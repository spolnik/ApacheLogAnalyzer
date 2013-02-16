using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace ApacheLogService.Service
{
    [ServiceContract]
    public interface IApacheLogService
    {
        [OperationContract]
        string[] GetAvailableProviders();

        [OperationContract]
        string[] GetErrorLogsTypes(string provider);

        [OperationContract]
        string[] GetAccessLogsTypes(string provider);

        [OperationContract]
        byte[] GetErrorLogsStatistics(string provider, DateTime from, DateTime to, string level, int imageWidth,
                                      int imageHeight);
        
        [OperationContract]
        byte[] GetAccessLogsStatistics(string provider, DateTime from, DateTime to, int imageWidth,
                                              int imageHeight);

        [OperationContract]
        byte[] GetMostFrequentErrorLogs(string provider, DateTime from, DateTime to, string type, int limit,
                                               int imageWidth, int imageHeight);

        [OperationContract]
        byte[] GetMostFrequentAccessLogs(string provider, DateTime from, DateTime to, string type, int limit,
                                                int imageWidth, int imageHeight);
    }

    [DataContract]
    public class Composite
    {
        public int num;
        public string str;

        public Composite(int num, string str)
        {
            this.num = num;
            this.str = str;
        }

        [DataMember]
        public int Num
        {
            get { return num; }
            set { num = value; }
        }

        [DataMember]
        public string Str
        {
            get { return str; }
            set { str = value; }
        }
    }

}
