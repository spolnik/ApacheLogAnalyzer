package ask.gui.imageForm;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import ask.data.DataSource;
import ask.data.DataSourceWrapper;
import ask.data.RemoteDataSource;
import ask.gui.imageChooser.ImageChooser;
import ask.gui.imagePanel.ImagePanel;

public class ImageFormPresenter implements ImageForm {

	private int height = 300;
	private int width = 300;
	private ImageFormDisplay display;
	public ImageFormPresenter(final ImageFormDisplay display, final ImageChooser presenter, String host, String port) throws ServiceException {
		this.display = display;
		final DataSource wrapper = new RemoteDataSource(host, port);
		display.setMasterTypes(new String[] {"ERROR_STATS", "ACCESS_STATS", "MF_ERROR", "MF_ACCESS"});
		display.setProviders(wrapper.getAvailableProviders());
		display.setTypes("ERROR_STATS", new String[] {"all", "notice", "error", "warn"});
		display.setTypes("ACCESS_STATS", new String[] {});
		display.setTypes("MF_ACCESS", wrapper.getAccessLogsTypes("localhost"));
		display.setTypes("MF_ERROR", wrapper.getErrorLogsTypes(display.getProvider()));
		display.addCommitFormListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date from = ImageFormPresenter.this.display.getDateFrom();
				Date to = ImageFormPresenter.this.display.getDateTo();
				String masterType = ImageFormPresenter.this.display.getMasterType();
				String type = ImageFormPresenter.this.display.getType();
				String provider = ImageFormPresenter.this.display.getProvider();
				int limit = ImageFormPresenter.this.display.getLimit();
				
				/*DataSource wrapper = null;
				
				try {
					wrapper = new RemoteDataSource("localhost", "8732");
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				Image image = null;
				if ("ACCESS_STATS".equals(masterType)) {
					image = wrapper.getAccessLogsStatistics(provider,from, to, height, width);
				} else if ("ERROR_STATS".equals(masterType)) {
					image = wrapper.getErrorLogsStatistics(provider,from, to, type.equals("all") ? null : type, height, width);
				} else if ("MF_ERROR".equals(masterType)) {
					image = wrapper.getMostFrequentErrorLogs(provider,from, to, type, limit, height, width);
				} else if ("MF_ACCESS".equals(masterType)) {
					image = wrapper.getMostFrequentAccessLogs(provider,from, to, type, limit, height, width);
				}
				presenter.addImage(image);
			}
			
		});
	}
	
	@Override
	public void showForm() {
		display.popup();
	}

}
