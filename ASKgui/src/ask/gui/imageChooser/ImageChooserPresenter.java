package ask.gui.imageChooser;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.rpc.ServiceException;

import ask.gui.imageForm.ImageFormDisplay;
import ask.gui.imageForm.ImageFormPresenter;
import ask.gui.imagePanel.ImagePanel;

public class ImageChooserPresenter implements ImageChooser {
	private ImageChooserDisplay display;
	private ImagePanel imagePanel;
	private List<Image> images = new ArrayList<Image>();
	private String host;
	private String port;
	
	public ImageChooserPresenter(ImageChooserDisplay display,
			ImagePanel imagePanel) {
		this.display = display;
		this.imagePanel = imagePanel;
		
		initListers();
	}

	private void initListers() {
		display.setAddHandler(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					addImageAction(display.getHost(), display.getPort());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		display.addChangeImageHandler(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				changeImageAction();
			}

		});
		display.setRemoveHandler(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeImageAction();
			}
		});
	}

	protected void changeImageAction() {
		Integer currentImage = display.getCurrentImage();
		Image image = null;
		if (images.size() > currentImage && currentImage >= 0) {
			image = images.get(currentImage);
		}
		imagePanel.setImage(image);
	}

	protected void removeImageAction() {
		int i = display.getCurrentImage();
		if (i < images.size() && i >= 0) {
			images.remove(i);
			int size = images.size() - 1;
			if (size < 0)
				size = 0;
			display.setImageCount(size);
		} else {
			images.clear();
			imagePanel.setImage(null);
		}
	}

	protected void addImageAction(String host, String port) throws ServiceException {
		ImageFormDisplay ifDispl = new ImageFormDisplay();
		ImageFormPresenter pres = new ImageFormPresenter(ifDispl, this, host, port);
		pres.showForm();
		// DataSourceWrapper wrapper = new DataSourceWrapper(null);
		// Image image = wrapper.getErrorLogs(null, null, null, 0, 200, 200);
		// images.add(image);
		// imagePanel.setImage(image);

	}

	@Override
	public void addImage(Image image) {
		if (image != null) {
			this.images.add(image);
			display.setImageCount(images.size() - 1);
			imagePanel.setImage(image);
		}
	}

}
