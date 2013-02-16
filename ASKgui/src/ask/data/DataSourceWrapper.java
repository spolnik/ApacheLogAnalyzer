package ask.data;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

public class DataSourceWrapper implements DataSource {
	
	private RemoteDataSource remote;

	public DataSourceWrapper(RemoteDataSource dataSource) {
		remote = dataSource;
	}
	
	@Override
	public String[] getAccessLogsTypes(String provider) {
		return new String[]{"A type 1", "A type 2", "A type 3"};
	}

	@Override
	public Image getErrorLogsStatistics(String provider, Date from, Date to, String level, int imageWidth,
            int imageHeight) {
		try {
			return ImageIO.read(new File("C:/testIMG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] getErrorLogsTypes(String provider) {
		return new String[]{"E type 1", "E type 2", "E type 3"};
	}

	@Override
	public Image getMostFrequentAccessLogs(String provider, Date from, Date to, String type,
			int limit, int imageW, int imageH) {
		return Toolkit.getDefaultToolkit().getImage("C:\testIMG.png");
	}

	@Override
	public Image getMostFrequentErrorLogs(String provider, Date from, Date to, String type,
			int limit, int imageW, int imageH) {
		return Toolkit.getDefaultToolkit().getImage("C:\testIMG.png");
	}

	@Override
	public Image getAccessLogsStatistics(String provider, Date from, Date to,
			int imageWidth, int imageHeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAvailableProviders() {
		// TODO Auto-generated method stub
		return null;
	}

}
