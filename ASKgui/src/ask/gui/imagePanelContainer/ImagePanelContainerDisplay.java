package ask.gui.imagePanelContainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;

import sun.awt.VerticalBagLayout;
import ask.gui.ASKDataPresenter;
import ask.gui.imagePanel.ImagePanel.ImagePanelDisplay;

public class ImagePanelContainerDisplay implements ImagePanelContainer.ImagePanelContainerDisplay{
	private Container mainComponent; 
	private int size = 0;
	public ImagePanelContainerDisplay() {
		VerticalBagLayout layout = new VerticalBagLayout();
//		GridBagLayout layout = new GridBagLayout();
//		GridLayout gridLayout = new GridLayout(0,1);
//		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		mainComponent = new Panel(layout);
		mainComponent.setBackground(Color.PINK);
	}
	
	@Override
	public void addImagePanel(ImagePanelDisplay imagePanel) {
		
		mainComponent.add(imagePanel.asComponent());
		mainComponent.setSize(new Dimension(mainComponent.getComponent(0).getHeight()*mainComponent.getComponentCount(), mainComponent.getComponent(0).getWidth()));
//		ASKDataPresenter.validateRoot();
	}

	@Override
	public void removeImagePanel(ImagePanelDisplay imagePanel) {
		mainComponent.remove(imagePanel.asComponent());
		size--;
		mainComponent.repaint();
//		ASKDataPresenter.validateRoot();
	}

	@Override
	public Component asComponent() {
		return mainComponent;
	}

}
