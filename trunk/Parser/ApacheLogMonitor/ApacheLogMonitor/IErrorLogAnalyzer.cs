namespace ApacheLogMonitor
{
    public interface IErrorLogAnalyzer
    {
        void Analyze(string path);

        ApacheLogsDataSet ApacheLogsDataSet { get; set; }
    }
}