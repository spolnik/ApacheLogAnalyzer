package ask.data;

import java.awt.Image;
import java.util.Date;

public interface DataSource {
    String[] getAvailableProviders();
    String[] getErrorLogsTypes(String provider);
    String[] getAccessLogsTypes(String provider);
    Image getErrorLogsStatistics(String provider, Date from, Date to, String level, int imageWidth,
                                  int imageHeight);
    Image getAccessLogsStatistics(String provider, Date from, Date to, int imageWidth,
                                          int imageHeight);
    Image getMostFrequentErrorLogs(String provider, Date from, Date to, String type, int limit,
                                           int imageWidth, int imageHeight);
    Image getMostFrequentAccessLogs(String provider, Date from, Date to, String type, int limit,
                                            int imageWidth, int imageHeight);
}
