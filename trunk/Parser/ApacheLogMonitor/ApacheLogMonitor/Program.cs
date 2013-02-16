
using System;

namespace ApacheLogMonitor
{
    class Program
    {
        static void Main()
        {
            Console.WriteLine("Apache 2.2 Logs Uploader starts ...");
            
            var configuration = new ApacheConfiguration();
            configuration.ErrorLogAnalyzer = new ErrorLogAnalyzer();
            configuration.InfoLogAnalyzer = new InfoLogAnalyzer();
            configuration.RegexBuilder = new ApacheRegexBuilder();
            
            configuration.Load();

            Console.WriteLine("Succes - logs informations are uploaded.");
            Console.WriteLine("Please type a key...");
            Console.ReadKey();
        }
    }
}
