using System;
using System.Collections.Generic;
using System.Xml;
using ApacheLogService.Charting;
using ApacheLogService.Service;
using DataAccessLayer;

namespace ApacheLogService.DAL
{
    public class ApacheLogService : IApacheLogService
    {
        public const string HostsFile = "LogHosts.xml";
     
        //returns ips/hostnames of available logs providers
        public string[] GetAvailableProviders()
        {
            List<string> hosts = new List<string>();
            XmlTextReader textReader = new XmlTextReader(HostsFile);

            // Read until end of file

            while (textReader.Read())
            {
                if(textReader.NodeType == XmlNodeType.Text)
                {
                    hosts.Add(textReader.Value);    
                }
            }

            textReader.Close();

            return hosts.ToArray();
        }

        //returns columns in error log table that can be used for statistic collection
        public string[] GetErrorLogsTypes(string provider)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            return dr.RetrieveErrorLogsTypes().ToArray();
        }

        //returns columns in info log table that can be used for statistic collection
        public string[] GetAccessLogsTypes(string provider)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            return dr.RetrieveAccessLogsTypes().ToArray();
        }

        //produces chart representing number of errors as  a function of date
        // e.g. GetErrorLogsStatistics [localhost] [Now - 10days] [Now + 10 days] [notice|error] [100] [100]
        public byte[] GetErrorLogsStatistics(string provider, DateTime from, DateTime to, string level, int imageWidth, int imageHeight)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            List<LogStatistic<DateTime, int>> values = dr.RetrieveErrorLogsStatistics(from, to, level);
            return ChartCreator.ChartToBytes(ChartCreator.GenerateBarChart(values, imageWidth, imageHeight));
        }

        // e.g. GetAccessLogsStatistics [localhost] [Now - 10days] [Now + 10 days] [100] [100]
        public byte[] GetAccessLogsStatistics(string provider, DateTime from, DateTime to, int imageWidth, int imageHeight)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            List<LogStatistic<DateTime, int>> values = dr.RetrieveAccessLogsStatistics(from, to);
            return ChartCreator.ChartToBytes(ChartCreator.GenerateBarChart(values, imageWidth, imageHeight));
        }


        //Beware of NULLS in columns! For the time being, don't make stats for columns that contain NULLs!
        //returns chart with 'limit' most frequent errors and sum others
        //e.g. GetMostFrequentErrorLogs  [localhost] [Now - 10days] [Now + 10 days] [one of returned by GetErrorLogTypes, e.g. Message] [100] [100]
        public byte[] GetMostFrequentErrorLogs(string provider, DateTime from, DateTime to, string type, int limit, int imageWidth, int imageHeight)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            List<LogStatistic<string, int>> values = dr.RetrieveMostFrequentErrorLogsStatistics(from, to, type);
            return ChartCreator.ChartToBytes(ChartCreator.GeneratePieChart(values, limit, imageWidth, imageHeight));
        }

        //e.g. GetMostFrequentAccessLogs  [localhost] [Now - 10days] [Now + 10 days] [one of returned by GetAccessLogTypes, e.g. FirstLineRequest] [100] [100]
        public byte[] GetMostFrequentAccessLogs(string provider, DateTime from, DateTime to, string type, int limit, int imageWidth, int imageHeight)
        {
            DataRetriever dr = DataRetriever.GetInstance(provider);
            List<LogStatistic<string, int>> values = dr.RetrieveMostFrequentAccessLogsStatistics(from, to, type);
            return ChartCreator.ChartToBytes(ChartCreator.GeneratePieChart(values, limit, imageWidth, imageHeight));
        }
    }
}
