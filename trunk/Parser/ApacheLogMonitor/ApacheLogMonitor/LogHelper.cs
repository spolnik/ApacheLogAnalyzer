using System;
using System.IO;

namespace ApacheLogMonitor
{
    public static class LogHelper
    {
        public static string CopyLog(string logPath)
        {
            if (!Directory.Exists("Logs"))
                Directory.CreateDirectory("Logs");

            string path = string.Concat("Logs\\", Path.GetFileNameWithoutExtension(logPath), '-', DateTime.Now.Ticks.ToString(),
                                        Path.GetExtension(logPath));
            File.Copy(logPath, path);
            return path;
        }
    }
}