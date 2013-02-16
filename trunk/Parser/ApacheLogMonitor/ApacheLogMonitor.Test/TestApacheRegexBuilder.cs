using System.IO;
using System.Text.RegularExpressions;
using NUnit.Framework;

namespace ApacheLogMonitor.Test
{
    [TestFixture]
    public class TestApacheRegexBuilder
    {
        private ApacheRegexBuilder _regexBuilder;
        private const string CommonLogFormat = "%h %l %u %t \\\"%r\\\" %>s %b";

        [SetUp]
        public void SetUp()
        {
            this._regexBuilder = new ApacheRegexBuilder();
        }

        [Test]
        public void Test()
        {
            var stringPattern = this._regexBuilder.Resolve(CommonLogFormat);
            var regex = new Regex(stringPattern);
            var reader = new StreamReader(@"..\..\Data\access.log");
            string line;
            int count = 0;

            while ((line = reader.ReadLine()) != null)
            {
                var match = regex.Match(line);
                Assert.IsTrue(match.Success);
                Assert.AreEqual(match.Groups["ip"].Value, "127.0.0.1");
                Assert.AreEqual(match.Groups["logname"].Value, "-");
                Assert.AreEqual(match.Groups["user"].Value, "-");
                Assert.AreEqual(match.Groups["day"].Value, "14");
                Assert.AreEqual(match.Groups["month"].Value, "Nov");
                Assert.AreEqual(match.Groups["year"].Value, "2010");
                Assert.IsTrue(match.Groups["request"].Value.StartsWith("GET"));
                if (match.Success)
                    count++;
            }

            Assert.AreEqual(count, 44);
        }
    }
}