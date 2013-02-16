package ask.gui.imagePanelContainer;

import java.util.HashSet;
import java.util.Set;

import ask.gui.imagePanel.ImagePanel;
import ask.gui.imagePanel.ImagePanelDisplay;
import ask.gui.imagePanel.ImagePanelPresenter;

public class ImagePanelContainerPresenter implements ImagePanelContainer{

	private ImagePanelContainerDisplay display;
	private Set<ImagePanel> panels = new HashSet<ImagePanel>();
	
	public ImagePanelContainerPresenter(ImagePanelContainerDisplay display) {
		this.display = display;
		checkSize();
	}

	@Override
	public void destroy(ImagePanel imagePanelPresenter) {
		if (panels.remove(imagePanelPresenter)) {
			this.display.removeImagePanel(imagePanelPresenter.getDisplay());
			checkSize();
		}
	}

	@Override
	public void checkSize() {
		if (getSize()==0) {
			createNewImagePanel();
		}
	}
	
	@Override
	public void createNewImagePanel() {
		ask.gui.imagePanel.ImagePanel.ImagePanelDisplay display = new ImagePanelDisplay(); 
		ImagePanel presenter = new ImagePanelPresenter(display);
		panels.add(presenter);
		presenter.setContainer(this);
		this.display.addImagePanel(display);
	}

	@Override
	public int getSize() {
		return panels.size();
	}

	@Override
	public void add(ImagePanel imagePanelPresenter) {
		if (panels.add(imagePanelPresenter)) {
			this.display.addImagePanel(imagePanelPresenter.getDisplay());
		}
	}
	
}
