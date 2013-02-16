
namespace ApacheLogMonitor
{
    public interface IApacheConfiguration
    {
        IApacheRegexBuilder RegexBuilder { get; set; }
        IErrorLogAnalyzer ErrorLogAnalyzer { get; set; }
        IInfoLogAnalyzer InfoLogAnalyzer { get; set; }

        string ErrorLogPath { get; }
        bool IsLogForensicModuleLoad { get; }
        bool IsLogConfigModuleLoad { get; }
        bool IsLogIoModuleLoad { get; }
        LogLevel LogLevel { get; }

        void Load();
    }
}