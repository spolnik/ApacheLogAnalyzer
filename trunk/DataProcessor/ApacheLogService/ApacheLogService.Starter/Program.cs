using System;
using System.ServiceModel;

namespace ApacheLogService.Starter
{
    class Program
    {
        static void Main(string[] args)
        {
            using (var serviceHost = new ServiceHost(typeof(DAL.ApacheLogService)))
            {
                Console.WriteLine("Server is starting ...");
                serviceHost.Open();
                Console.WriteLine("Server is started.");
                Console.WriteLine("Type [ENTER] to stop server ... ");
                Console.ReadLine();
                Console.WriteLine("Server is stopping ...");
            }

            Console.WriteLine("Server is stopped.");
            Console.WriteLine("Type [ENTER] to exit ... ");
            Console.ReadLine();
        }
    }
}
