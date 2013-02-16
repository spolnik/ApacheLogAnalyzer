using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Text.RegularExpressions;

namespace ApacheLogMonitor
{
    public class InfoLogAnalyzer : IInfoLogAnalyzer
    {
        private const string InfoLogDateTimeFormat = "dd/MMM/yyyy:HH:mm:ss";
        private string _fileName;
        private string _logName;
        private string _logPath;
        
        private readonly Dictionary<string, Regex> _regexCache = new Dictionary<string,Regex>();

        #region Implementation of IInfoLogAnalyzer

        public void Analyze(string logName, string logPath, string logPattern)
        {
            this._logName = logName;
            this._logPath = logPath;
            this._fileName = LogHelper.CopyLog(logPath);

            using (var reader = new StreamReader(this._fileName))
            {
                string line;

                while ((line = reader.ReadLine()) != null)
                {
                    if (!this._regexCache.ContainsKey(logPattern))
                        this._regexCache.Add(logPattern, new Regex(logPattern));
                    
                    var match = this._regexCache[logPattern].Match(line);

                    try
                    {
                        ApacheLogsDataSet.InfoLogTableRow row = this.GetRow(match);

                        this.ApacheLogsDataSet.InfoLogTable.AddInfoLogTableRow(row);
                    }
                    catch
                    {
                        Console.WriteLine("Error, cannot convert:");
                        Console.WriteLine("Line: {0}", line);
                        Console.WriteLine("Match: {0}", match.Value);
                        Console.WriteLine("=======================");
                        throw;
                    }
                }
            }
        }

        public ApacheLogsDataSet ApacheLogsDataSet { get; set; }

        private ApacheLogsDataSet.InfoLogTableRow GetRow(Match match)
        {
            var row = this.ApacheLogsDataSet.InfoLogTable.NewInfoLogTableRow();

            row.LogName = this._logName;
            row.LogPath = this._logPath;

            if (match.Groups["ip"].Success)
                row.RemoteHost = match.Groups["ip"].Value;

            if (match.Groups["logname"].Success)
                row.RemoteLogname = match.Groups["logname"].Value;

            if (match.Groups["user"].Success)
                row.RemoteUser = match.Groups["user"].Value;

            if (match.Groups["datetime"].Success)
            {
                var datetime = match.Groups["datetime"].Value;
                datetime = datetime.Substring(0, datetime.Length - 6);
                var result = DateTime.ParseExact(datetime, InfoLogDateTimeFormat, CultureInfo.InvariantCulture);
                row.Time = result;
            }

            if (match.Groups["request"].Success)
                row.FirstLineRequest = match.Groups["request"].Value;

            if (match.Groups["status"].Success)
                row.Status = Int32.Parse(match.Groups["status"].Value);

            if (match.Groups["size"].Success)
                row.Status = Int32.Parse(match.Groups["size"].Value);

            return row;
        }

        #endregion
    }
}