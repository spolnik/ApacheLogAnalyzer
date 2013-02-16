package ask.gui.imageChooser;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

public interface ImageChooser {
	
	public interface ImageChooserDisplay {
		void setAddHandler(ActionListener listener);
		void setRemoveHandler(ActionListener listener);
		Integer getCurrentImage();
		void addChangeImageHandler(ChangeListener listenr);
		Component addConponnet();
		void setImageCount(int i);
		public String getHost();
		public String getPort();
		
	}

	void addImage(Image image);
	
}
