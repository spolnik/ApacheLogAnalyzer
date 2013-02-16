using NUnit.Framework;

namespace ApacheLogMonitor.Test
{
    [TestFixture]
    public class TestApacheConfiguration
    {
        private ApacheConfiguration _configuration;

        [SetUp]
        public void SetUp()
        {
            this._configuration = new ApacheConfiguration();
            this._configuration.Load();
        }

        [Test]
        public void CheckLoadedModules()
        {
            Assert.IsTrue(this._configuration.IsLogConfigModuleLoad);
            Assert.IsFalse(this._configuration.IsLogIoModuleLoad);
            Assert.IsFalse(this._configuration.IsLogForensicModuleLoad);
        }

        [Test]
        public void CheckApachePaths()
        {
            Assert.AreEqual(this._configuration.ErrorLogPath, @"c:\Program Files\Apache Software Foundation\Apache2.2\logs\error.log");
        }
    }
}
