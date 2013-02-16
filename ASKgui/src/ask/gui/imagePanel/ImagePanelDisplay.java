package ask.gui.imagePanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanelDisplay implements ImagePanel.ImagePanelDisplay {
	private Image img = null;
	private JPanel mainComponent = new JPanel() {
		// @Override
		// public void paint(Graphics g) {
		// super.paint(g);
		// if (img != null) {
		// g.drawImage(img, 0, 0, img.getWidth(mainComponent), img
		// .getHeight(mainComponent), mainComponent);
		// this.validate();
		// } else {
		// g.drawRect(0, 0, mainComponent.getWidth(), getHeight());
		// }
		// }
	};
	private Button button;

	public ImagePanelDisplay() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
//		button = new Button("[ add ]");
		mainComponent.setLayout(layout);
//		mainComponent.add(button);
//		mainComponent.setPreferredSize(new Dimension(200, 200));
		mainComponent.validate();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setAddImageListener(ActionListener actionListener) {
//		button.addActionListener(actionListener);
	}

	@Override
	public void setImage(final Image image) {
		img = image;
		mainComponent.removeAll();
		if (img != null)
			mainComponent.add(new JLabel(new ImageIcon(img)));
		mainComponent.validate();
		mainComponent.repaint();
		// Icon icon = new ImageIcon(image);
		// mainComponent.add(new JLabel(icon), BorderLayout.CENTER);
		// mainComponent.repaint();
	}

	@Override
	public void setEditImageListener(ActionListener actionListener) {
		button.addActionListener(actionListener);
	}

	@Override
	public Component asComponent() {
		return mainComponent;
	}

	@Override
	public void setButtonRemove() {
		mainComponent.remove(button);
		button = new Button("[ edit ]");
		mainComponent.add(button, BorderLayout.NORTH);
	}

}
