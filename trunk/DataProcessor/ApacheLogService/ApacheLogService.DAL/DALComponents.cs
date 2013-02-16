using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using DataAccessLayer;

namespace ApacheLogService.DAL
{
    public class DataRetriever : IDisposable
    {
        private const string _errorLogTableName = "ErrorLogTable";
        private const string _infoLogTableName = "InfoLogTable";
        private static readonly HashSet<string> errorExcludeList = new HashSet<string>();
        private static readonly HashSet<string> accessExcludeList = new HashSet<string>();
        private static Dictionary<string, DataRetriever> _instances;

        private readonly string connectionString;
        private SqlConnection _connection;
        private string _dbName = "ApacheLogs";
        private string _serverIp;

        static DataRetriever()
        {
            errorExcludeList.Add("ID");
            errorExcludeList.Add("Date");

            accessExcludeList.Add("ID");
            accessExcludeList.Add("Time");
            _instances = new Dictionary<string, DataRetriever>();
        }

        private DataRetriever(string serverIP)
        {
            _serverIp = serverIP;
            connectionString = "server=" + serverIP + "\\SQLExpress;integrated security=SSPI;database=" + _dbName;
        }

        public bool IsConnectionActive()
        {
            return Connection == null ? false : true;
        }

        private SqlConnection Connection
        {
            get
            {
                Open();
                return _connection;
            }
        }

        #region IDisposable Members

        public void Dispose()
        {
            Close();
        }

        #endregion

        public static DataRetriever GetInstance(string serverIP)
        {
            DataRetriever dr = null;

            if (!_instances.ContainsKey(serverIP))
            {
                dr = new DataRetriever(serverIP);
                _instances.Add(serverIP, dr);
            }
            else
            {
                dr = _instances[serverIP];
            }

            return dr;
        }

        private void Open()
        {
            if (_connection == null)
            {
                _connection = new SqlConnection(connectionString);
                try
                {
                    _connection.Open();
                    //Console.WriteLine("Connection opened successfully.");
                }
                catch (Exception)
                {
                    //Console.WriteLine("Failure when opening connection.\n{0}", connectionString);
                    Close();
                }
            }
        }

        private void Close()
        {
            if (_connection != null)
            {
                try
                {
                    _connection.Close();
                }
                catch (Exception)
                {
                    //Console.WriteLine("Failure when closing connection.");
                    //System.Console.WriteLine(e.Message);
                }
                finally
                {
                    _connection = null;
                }
            }
        }

        public List<string> RetrieveErrorLogsTypes()
        {
            return RetrieveLogsTypes(_errorLogTableName, errorExcludeList);
        }

        public List<string> RetrieveAccessLogsTypes()
        {
            return RetrieveLogsTypes(_infoLogTableName, accessExcludeList);
        }

        public List<string> RetrieveLogsTypes(string tableName, HashSet<string> excluded)
        {
            var result = new List<string>();

            string query =
                @"use ApacheLogs
                    SELECT ColumnName=syscolumns.name
                    FROM sysobjects 
                    JOIN syscolumns ON sysobjects.id = syscolumns.id
                    JOIN systypes ON syscolumns.xtype=systypes.xtype
                    WHERE sysobjects.xtype='U' and sysobjects.name = '" +
                tableName +
                @"'
                    and systypes.name != 'sysname'
                    ORDER BY sysobjects.name,syscolumns.colid";

            var cmd = new SqlCommand(query, Connection);
            SqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                var val = (string) rdr[0];
                if (!excluded.Contains(val))
                {
                    result.Add(val);
                }
            }

            rdr.Close();

            return result;
        }

        public List<LogStatistic<DateTime, int>> RetrieveErrorLogsStatistics(DateTime from, DateTime to, string level)
        {
            var result = new List<LogStatistic<DateTime, int>>();

            string whereClause = "";

            if (level != null)
            {
                whereClause = " where e.type = '" + level + @"' ";
            }

            string query =
                @"use apacheLogs
                    select cast([DATE] as date), COUNT(*) from ErrorLogTable as e"
                + whereClause +
                @" group by cast([DATE] as date)";

            //Console.WriteLine(query);

            var cmd = new SqlCommand(query, Connection);
            SqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                DateTime dateTime = ((DateTime) rdr[0]).Date;
                var val = (int) rdr[1];

                if ((dateTime >= from.Date) && (dateTime <= to.Date))
                {
                    result.Add(new LogStatistic<DateTime, int>(dateTime, val));
                }
            }

            rdr.Close();

            return result;
        }

        public List<LogStatistic<DateTime, int>> RetrieveAccessLogsStatistics(DateTime from, DateTime to)
        {
            var result = new List<LogStatistic<DateTime, int>>();

            string query =
                @"use apacheLogs
                    select cast([TIME] as date), COUNT(*) from InfoLogTable as e" +
                @" group by cast([TIME] as date)";

            Console.WriteLine(query);

            var cmd = new SqlCommand(query, Connection);
            SqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                DateTime dateTime = ((DateTime)rdr[0]).Date;
                var val = (int)rdr[1];

                if ((dateTime >= from.Date) && (dateTime <= to.Date))
                {
                    result.Add(new LogStatistic<DateTime, int>(dateTime, val));
                }
            }

            rdr.Close();

            return result;
        }

        private static void Main(string[] args)
        {
            /*var dal = DataRetriever.GetInstance("localhost");*/
            /*
            System.Console.WriteLine(">>Error Types");
            foreach(var a in dal.RetrieveErrorLogsTypes())
            {
                System.Console.WriteLine(a);
            }

            System.Console.WriteLine(">>Access Types");
            foreach (var a in dal.RetrieveAccessLogsTypes())
            {
                System.Console.WriteLine(a);
            }

            //dal.Close();
            
            System.Console.ReadLine();*/
            /*System.Console.WriteLine(new DateTime().ToString());
            System.Console.ReadLine();*/
            /*var logs = dal.RetrieveMostFrequentErrorLogsStatistics(DateTime.Now.Subtract(new  TimeSpan(30, 0 ,0 ,0)), DateTime.Now, "Message");

            foreach (var logStatistic in logs)
            {
                Console.WriteLine(logStatistic.Key + " " + logStatistic.Value);
            }
            Console.ReadLine();*/
        }

        public List<LogStatistic<string, int>> RetrieveMostFrequentLogs(DateTime from, DateTime to, string table, string field, string dateColumn)
        {
            var result = new List<LogStatistic<string, int>>();

            string whereClause = " where " + dateColumn + " >= '" + from.ToString() + "' and " + dateColumn + " <= '" + to.ToString() + "' ";

            string query = @"use ApacheLogs
                            select  CAST(" + field + " AS nvarchar(1024)), COUNT(*) from " + table
                            + whereClause +                         
                            @"group by " + field + @"
                            order by COUNT(*) desc
                            ";

            //Console.WriteLine(query);

            var cmd = new SqlCommand(query, Connection);
            SqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                string message = "NULL";
                var v = rdr[0] as DBNull;
                if(v == null)
                    message = (string) rdr[0];

                int count = (int)rdr[1];

                result.Add(new LogStatistic<string, int>(message, count));
            }

            rdr.Close();

            return result;
        }


        public List<LogStatistic<string, int>> RetrieveMostFrequentErrorLogsStatistics(DateTime from, DateTime to, string attribute)
        {
            return RetrieveMostFrequentLogs(from, to, _errorLogTableName, attribute, "Date");
        }

        public List<LogStatistic<string, int>> RetrieveMostFrequentAccessLogsStatistics(DateTime from, DateTime to, string attribute)
        {
            return RetrieveMostFrequentLogs(from, to, _infoLogTableName, attribute, "Time");
        }
    }
}