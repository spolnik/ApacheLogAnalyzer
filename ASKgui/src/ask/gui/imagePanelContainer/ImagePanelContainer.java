package ask.gui.imagePanelContainer;

import ask.gui.Display;
import ask.gui.imagePanel.ImagePanel;
import ask.gui.imagePanel.ImagePanel.ImagePanelDisplay;

public interface ImagePanelContainer {

	void destroy(ImagePanel imagePanelPresenter);
	void add(ImagePanel imagePanelPresenter);
	int getSize();
	void checkSize();
	void createNewImagePanel();
	
	public interface ImagePanelContainerDisplay extends Display {
		void addImagePanel(ImagePanelDisplay imagePanel);
		void removeImagePanel(ImagePanelDisplay imagePanel);
	}

	
	
}
