using System;
using System.Data.SqlClient;
using ApacheLogMonitor.ApacheLogsDataSetTableAdapters;
using ApacheLogMonitor.Properties;

namespace ApacheLogMonitor
{
    internal static class DbHelper
    {
        private static readonly SqlConnection _connection = new SqlConnection(Settings.Default.ApacheLogsConnectionString);

        internal static void UpdateApacheLogsDatabase(ApacheLogsDataSet dataSet)
        {
            var errorLogTableTableAdapter = new ErrorLogTableTableAdapter();
            var infoLogTableTableAdapter = new InfoLogTableTableAdapter();

            try
            {
                _connection.Open();

                errorLogTableTableAdapter.Connection = _connection;
                infoLogTableTableAdapter.Connection = _connection;

                errorLogTableTableAdapter.Update(dataSet.ErrorLogTable);
                infoLogTableTableAdapter.Update(dataSet.InfoLogTable);

                dataSet.AcceptChanges();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                _connection.Close();
            }
        }
    }
}