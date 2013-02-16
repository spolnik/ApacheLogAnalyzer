
using System;
using System.Globalization;
using System.IO;
using System.Text.RegularExpressions;

namespace ApacheLogMonitor
{
    public class ErrorLogAnalyzer : IErrorLogAnalyzer
    {
        private const string ErrorLogLinePattern = "\\[(?<datetime>.+)\\] \\[(?<severity>.+)\\] \\[(?<ip>.+)\\] (?<message>.+)";
        private const string ErrorLogLinePatternShort = "\\[(?<datetime>.+)\\] \\[(?<severity>.+)\\] (?<message>.+)";
        private const string ErrorLogDateTimeFormat = "ddd MMM dd HH:mm:ss yyyy";
        private string _fileName;
        private static readonly Regex _errorLogLinePatternRegex = new Regex(ErrorLogLinePattern);
        private static readonly Regex _errorLogLinePatternShortRegex = new Regex(ErrorLogLinePatternShort);

        #region Implementation of IErrorLogAnalyzer

        public void Analyze(string path)
        {
            this._fileName = LogHelper.CopyLog(path);


            using (var reader = new StreamReader(this._fileName))
            {
                string line;
                
                while ((line = reader.ReadLine()) != null)
                {
                    if (!line.StartsWith("["))
                        continue;

                    var match = _errorLogLinePatternRegex.Match(line);
                    if (match.Success == false)
                        match = _errorLogLinePatternShortRegex.Match(line);

                    try
                    {
                        ApacheLogsDataSet.ErrorLogTableRow row = GetRow(match);

                        this.ApacheLogsDataSet.ErrorLogTable.AddErrorLogTableRow(row);
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

        private ApacheLogsDataSet.ErrorLogTableRow GetRow(Match match)
        {
            var row = this.ApacheLogsDataSet.ErrorLogTable.NewErrorLogTableRow();
            var result = DateTime.ParseExact(match.Groups["datetime"].Value, ErrorLogDateTimeFormat, CultureInfo.InvariantCulture);
            row.Date = result;
            row.Type = match.Groups["severity"].Value;
            row.Message = match.Groups["message"].Value;
            if (match.Groups["ip"].Success)
                row.IP = match.Groups["ip"].Value;
            return row;
        }

        #endregion
    }
}