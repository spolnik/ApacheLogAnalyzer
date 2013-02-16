using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Text.RegularExpressions;

namespace ApacheLogMonitor
{
    public class ApacheConfiguration : IApacheConfiguration
    {
        private string _apachePath;
        private string _apacheConfPath;
        private Dictionary<string, string> _logFormats;
        private Dictionary<string, string> _logPatterns;
        private Dictionary<string, string> _logFiles;
        private ApacheLogsDataSet _apacheLogsDataSet;

        public IApacheRegexBuilder RegexBuilder { get; set; }

        public IErrorLogAnalyzer ErrorLogAnalyzer { get; set; }

        public IInfoLogAnalyzer InfoLogAnalyzer { get; set; }

        public string ErrorLogPath { get; private set; }

        public void Load()
        {
            this._apacheLogsDataSet = new ApacheLogsDataSet();

            this._apachePath = ConfigurationManager.AppSettings["APACHE_HOME"];
            this._apacheConfPath = string.Concat(this._apachePath, @"conf\httpd.conf");
            
            this._logFormats = new Dictionary<string, string>();
            this._logPatterns = new Dictionary<string, string>();
            this._logFiles = new Dictionary<string, string>();

            this.IsLogIoModuleLoad = false;
            this.IsLogConfigModuleLoad = false;
            this.IsLogForensicModuleLoad = false;

            this.EvalApacheConf();
            this.PrepareLogPatterns();

            this.EvalErrorLog();
            this.EvalLogs();
            this.SaveData();
        }

        private void SaveData()
        {
            DbHelper.UpdateApacheLogsDatabase(this._apacheLogsDataSet);
        }

        private void EvalLogs()
        {
            this.InfoLogAnalyzer.ApacheLogsDataSet = this._apacheLogsDataSet;

            foreach (var logName in this._logFiles.Keys)
            {
                this.InfoLogAnalyzer.Analyze(logName, this._logFiles[logName], this._logPatterns[logName]);
            }
        }

        private void EvalErrorLog()
        {
            if (string.IsNullOrEmpty(this.ErrorLogPath))
                return;

            this.ErrorLogAnalyzer.ApacheLogsDataSet = this._apacheLogsDataSet;
            this.ErrorLogAnalyzer.Analyze(this.ErrorLogPath);
        }

        private void PrepareLogPatterns()
        {
            foreach (var key in this._logFormats.Keys)
                this._logPatterns.Add(key, this.RegexBuilder.Resolve(this._logFormats[key]));
        }

        public bool IsLogForensicModuleLoad { get; private set; }

        public bool IsLogConfigModuleLoad { get; private set; }

        public bool IsLogIoModuleLoad { get; private set; }

        private void EvalApacheConf()
        {
            using (var reader = new StreamReader(this._apacheConfPath))
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    if (line.StartsWith("#"))
                        continue;

                    line = line.TrimStart();

                    if (line.StartsWith("LoadModule logio"))
                    {
                        this.IsLogIoModuleLoad = true;
                        continue;
                    }

                    if (line.StartsWith("LoadModule log_config"))
                    {
                        this.IsLogConfigModuleLoad = true;
                        continue;
                    }
                
                    if (line.StartsWith("LoadModule log_forensic"))
                    {
                        this.IsLogForensicModuleLoad = true;
                        continue;
                    }

                    if (line.StartsWith("ErrorLog"))
                    {
                        var regex = new Regex("ErrorLog \"(?<path>.+)\"");
                        var match = regex.Match(line);
                        var path = match.Groups["path"].Value.Replace('/', '\\');
                        this.ErrorLogPath = string.Concat(this._apachePath, path);
                        continue;
                    }

                    if (line.StartsWith("LogLevel"))
                    {
                        var level = line.Split(' ')[1];
                        this.LogLevel = (LogLevel)Enum.Parse(typeof(LogLevel), level);
                        continue;
                    }

                    if (line.StartsWith("LogFormat"))
                    {
                        var regex = new Regex("LogFormat \"(?<pattern>.+)\" (?<name>\\w+)");
                        var match = regex.Match(line);
                        this._logFormats.Add(match.Groups["name"].Value, match.Groups["pattern"].Value);
                        continue;
                    }

                    if (line.StartsWith("CustomLog"))
                    {
                        var regex = new Regex("CustomLog \"(?<path>.+)\" (?<name>\\w+)");
                        var match = regex.Match(line);
                        var path = match.Groups["path"].Value.Replace('/', '\\');
                        this._logFiles.Add(match.Groups["name"].Value, string.Concat(this._apachePath, path));
                        continue;
                    }
                }
            }
        }

        public LogLevel LogLevel { get; private set; }
    }
}