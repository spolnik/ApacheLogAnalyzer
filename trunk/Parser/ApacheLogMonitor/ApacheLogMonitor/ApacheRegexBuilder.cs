using System.Text;

namespace ApacheLogMonitor
{
    public class ApacheRegexBuilder : IApacheRegexBuilder
    {
        public string Resolve(string logFormat)
        {
            string[] formatParts = logFormat.Split(' ');
            var regexBuilder = new StringBuilder();

            foreach (var formatPart in formatParts)
                regexBuilder.Append(string.Concat(ResolvePattern(formatPart), ' '));

            regexBuilder.Remove(regexBuilder.Length - 1, 1);
            return regexBuilder.ToString();
        }

        private static string ResolvePattern(string formatPart)
        {
            if (string.Equals(formatPart, "%h"))
                return "(?<ip>\\d{1,4}\\.\\d{1,4}\\.\\d{1,4}\\.\\d{1,4})";

            if (string.Equals(formatPart, "%l"))
                return "(?<logname>\\w+|[-])";

            if (string.Equals(formatPart, "%u"))
                return "(?<user>\\w+|[-])";

            if (string.Equals(formatPart, "%t"))
                return
                    "\\[(?<datetime>(?<day>\\d{2})/(?<month>\\w{3})/(?<year>\\d{4}):(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2}) (?<zone>[+|-]\\d{4}))\\]";

            if (string.Equals(formatPart, "\\\"%r\\\""))
                return "\"(?<request>.+)\"";

            if (string.Equals(formatPart, "%>s"))
                return "(?<status>\\d+)";
            
            if (string.Equals(formatPart, "%b"))
                return "(?<size>\\d+|[-])";

            return string.Equals(formatPart, "%%") ? "%" : string.Empty;
        }
    }
}