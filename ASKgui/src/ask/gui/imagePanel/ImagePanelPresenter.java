package ask.gui.imagePanel;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ask.data.DataSourceWrapper;
import ask.gui.imagePanelContainer.ImagePanelContainer;

public class ImagePanelPresenter implements ImagePanel {

	private ImagePanelDisplay display;
	private ImagePanelContainer container;

	public ImagePanelPresenter(final ImagePanel.ImagePanelDisplay display) {
		this.display = display;

		display.setAddImageListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.setButtonRemove();
				//DataSourceWrapper wrapper = new DataSourceWrapper(null);
				//display.setImage(wrapper.getErrorLogsStatistics("localhost", null, null, null, 200,
				//		200));
				display.setEditImageListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

					}
				});
			}
		});

	}

	@Override
	public void setContainer(ImagePanelContainer container) {
		this.container = container;
	}

	@Override
	public ImagePanelDisplay getDisplay() {
		return display;
	}

	@Override
	public void setImage(Image image) {
		display.setImage(image);
		// images.add(image);
	}

}
