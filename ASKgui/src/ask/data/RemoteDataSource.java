package ask.data;

import java.awt.Image;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.tempuri.ApacheLogServiceLocator;
import org.tempuri.IApacheLogService;

public class RemoteDataSource implements DataSource {
	private IApacheLogService ws;
	
	public RemoteDataSource(String address, String port) throws ServiceException {
		ApacheLogServiceLocator alsl = new ApacheLogServiceLocator();
		//System.out.println("http://" + address + ":" + port + "/ApacheLogMonitorService/");
		alsl.setEndpointAddress("BasicHttpBinding_IApacheLogService", "http://" + address + ":" + port + "/ApacheLogMonitorService/");
		ws = alsl.getBasicHttpBinding_IApacheLogService();
	}

	@Override
	public Image getAccessLogsStatistics(String provider, Date from, Date to,
			int imageWidth, int imageHeight) {
		try {
			return bytesToImage(ws.getAccessLogsStatistics(provider, dateToCalendar(from), dateToCalendar(to), imageWidth, imageHeight));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] getAccessLogsTypes(String provider) {
		try {
			return ws.getAccessLogsTypes(provider);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] getAvailableProviders() {
		try {
			return ws.getAvailableProviders();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Image getErrorLogsStatistics(String provider, Date from, Date to,
			String level, int imageWidth, int imageHeight) {
		try {
			//System.out.println(">" + level + "<");
			return bytesToImage(ws.getErrorLogsStatistics(provider, dateToCalendar(from), dateToCalendar(to), level, imageWidth, imageHeight));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] getErrorLogsTypes(String provider) {
		try {
			return ws.getErrorLogsTypes(provider);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Image getMostFrequentAccessLogs(String provider, Date from, Date to,
			String type, int limit, int imageWidth, int imageHeight) {
		try {
			return bytesToImage(ws.getMostFrequentAccessLogs(provider, dateToCalendar(from), dateToCalendar(to), type, limit, imageWidth, imageHeight));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Image getMostFrequentErrorLogs(String provider, Date from, Date to,
			String type, int limit, int imageWidth, int imageHeight) {
		try {
			return bytesToImage(ws.getMostFrequentErrorLogs(provider, dateToCalendar(from), dateToCalendar(to), type, limit, imageWidth, imageHeight));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Image bytesToImage(byte[] bytes)
	{
		return Toolkit.getDefaultToolkit().createImage(bytes);
	}
	
	private Calendar dateToCalendar(Date date)
	{
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl;
	}
	
	public static void main(String[] args) throws ServiceException
	{
		RemoteDataSource rds = new RemoteDataSource("127.0.0.1", "8732");
		String[] tab = rds.getAvailableProviders();
		
		for (String string : tab) {
			System.out.println(string);
		}
	}
}
