using System;
using System.Collections.Generic;
using System.IO;
using System.Web.UI.DataVisualization.Charting;
using DataAccessLayer;

namespace ApacheLogService.Charting
{
    public class ChartCreator
    {
        public static Chart GenerateBarChart(IList<LogStatistic<DateTime, int>> values, int width, int height)
        {
            Chart ch = new Chart();
            
            ch.ChartAreas.Add(new ChartArea());

            List<string> dates = new List<string>();
            List<int> counts = new List<int>();

            foreach (var tuple in values)
            {
                dates.Add(tuple.Key.Date.ToShortDateString());
                counts.Add(tuple.Value);
            }

            int[] yval = counts.ToArray();

            // Initialize an array of strings
            string[] xval = dates.ToArray();


            Series series = new Series("Default");
            series.ChartType = SeriesChartType.Bar;

            // Add series into the chart's series collection
            ch.Series.Add(series);
            // Bind the double array to the Y axis points of the Default data series
            ch.Series["Default"].Points.DataBindXY(xval, yval);

            ch.Width = width;
            ch.Height = height;

            return ch;
        }

        public static byte[] ChartToBytes(Chart ch)
        {
            
            var fs = new FileStream(@"D:\serializedImage.jpg", FileMode.Create);
            ch.SaveImage(fs, ChartImageFormat.Jpeg);
            fs.Close();
            var ms = new MemoryStream(); 
            ch.SaveImage(ms, ChartImageFormat.Jpeg);
            
            return ms.GetBuffer();
        }

        public static void Main(string[] args)
        {
            /*List<DataPoint> points = new List<DataPoint>();
            points.Add(new DataPoint(0.0, 2.0));
            points.Add(new DataPoint(1.0, 5.0));
            points.Add(new DataPoint(0.5, 3.0));*/
           /* Chart ch = GenerateBarChart(null);
            byte[] bytes = ChartToBytes(ch);*/
        }

        public static Chart GeneratePieChart(List<LogStatistic<string, int>> values, int limit, int imageWidth, int imageHeight)
        {
            Chart ch = new Chart();

            Series series = new Series("Default");

            ch.ChartAreas.Add(new ChartArea("Default"));

            ch.Series.Add(series);

            List<string> messages = new List<string>();
            List<int> counts = new List<int>();

            foreach (var tuple in values)
            {
                messages.Add(tuple.Key);
                counts.Add(tuple.Value);
            }

            int tmp = limit;
            //threshold
            int threshold = 0;
            for (int i = 0; i < counts.Count; i++)
            {
                tmp--;
                if(tmp == 0)
                {
                    threshold = counts[i];
                    break;
                }
            }

            int[] yValues = counts.ToArray();
            string[] xValues = messages.ToArray();

            ch.Series["Default"].Points.DataBindXY(xValues, yValues);

            // Set Doughnut chart type
            ch.Series["Default"].ChartType = SeriesChartType.Doughnut;

            // Set labels style
            ch.Series["Default"]["PieLabelStyle"] = "Outside";

            // Set Doughnut radius percentage
            ch.Series["Default"]["DoughnutRadius"] = "30";

            // Explode data point with label "Italy"
            //ch.Series["Default"].Points[4]["Exploded"] = "true";

            ///////COLLECTING
            if (limit < counts.Count)
            {
                ch.Series["Default"]["CollectedThreshold"] = "" + (threshold - 1) + "";

                // Set the threshold type to be in percentage
                // When set to false, this property uses the actual value to determine the collected threshold
                ch.Series["Default"]["CollectedThresholdUsePercent"] = "false";
            }
            // Set the label of the collected pie slice
            ch.Series["Default"]["CollectedLabel"] = "Other";

            // Set the legend text of the collected pie slice
            ch.Series["Default"]["CollectedLegendText"] = "Other";

            // Set the collected pie slice to be exploded
            ch.Series["Default"]["CollectedSliceExploded"] = "true";

            // Set the color of the collected pie slice
            ch.Series["Default"]["CollectedColor"] = "Green";

            // Set the tooltip of the collected pie slice
            ch.Series["Default"]["CollectedToolTip"] = "Other";

            /////////////////


            // Enable 3D
            ch.ChartAreas["Default"].Area3DStyle.Enable3D = true;

            ch.Legends.Add(new Legend("Default"));
            ch.Legends[0].Enabled = false;

            return ch;
        }
    }
}