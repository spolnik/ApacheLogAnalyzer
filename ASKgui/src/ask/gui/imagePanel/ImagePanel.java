package ask.gui.imagePanel;

import java.awt.Image;
import java.awt.event.ActionListener;

import ask.gui.Display;
import ask.gui.imagePanelContainer.ImagePanelContainer;

public interface ImagePanel {

	public ImagePanelDisplay getDisplay();

	void setContainer(ImagePanelContainer container);
	
	void setImage(Image image);
	
	public interface ImagePanelDisplay extends Display {
		void setImage(Image image);

		void setAddImageListener(ActionListener actionListener);

		void setEditImageListener(ActionListener actionListener);

		void destroy();

		void setButtonRemove();
	}

}
