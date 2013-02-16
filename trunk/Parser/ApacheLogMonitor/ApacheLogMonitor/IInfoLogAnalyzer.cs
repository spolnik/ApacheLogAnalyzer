namespace ApacheLogMonitor
{
    public interface IInfoLogAnalyzer
    {
        void Analyze(string logName, string logPath, string logPattern);

        ApacheLogsDataSet ApacheLogsDataSet { get; set; }
    }
}