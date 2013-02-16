package ask.gui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import sun.awt.VerticalBagLayout;

import ask.gui.imageChooser.ImageChooserDisplay;
import ask.gui.imageChooser.ImageChooserPresenter;
import ask.gui.imagePanel.ImagePanelDisplay;
import ask.gui.imagePanel.ImagePanelPresenter;
import ask.gui.imagePanelContainer.ImagePanelContainerPresenter;
import ask.gui.imagePanelContainer.ImagePanelContainer.ImagePanelContainerDisplay;

public class ASKDataPresenter extends Applet {
	private static final long serialVersionUID = -3825583794837489931L;
	private static Container parent;
	private static JScrollPane pane;

	
	// @Override
	public void init() {
		// FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		BorderLayout layout = new BorderLayout();
		parent = this;
		// layout.setHgap(0);
		// layout.setVgap(0);
		this.setLayout(layout);
		this.setBackground(Color.BLACK);
		
		ImagePanelDisplay display = new ImagePanelDisplay();
		display.asComponent().setSize(new Dimension(100,100));
		ImagePanelPresenter presenter = new ImagePanelPresenter(display);
		
		final JScrollPane pane = new JScrollPane(display.asComponent());
//		VerticalBagLayout layout2 = new VerticalBagLayout();
//		pane.setLayout(layout2);
//		pane.add();
		ImageChooserDisplay disp = new ImageChooserDisplay();
		ImageChooserPresenter pres = new ImageChooserPresenter(disp, presenter);
		this.add(disp.addConponnet(),BorderLayout.NORTH);
		this.add(pane, BorderLayout.CENTER);
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				pane.setSize(arg0.getComponent().getSize());
				pane.setPreferredSize(arg0.getComponent().getSize());
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				pane.setSize(arg0.getComponent().getSize());
				pane.setPreferredSize(arg0.getComponent().getSize());
			}
		
		});
		this.setSize(640, 480);
	}

	// pane.setBackground(Color.LIGHT_GRAY);
	// final ImagePanelContainerDisplay display = new
	// ask.gui.imagePanelContainer.ImagePanelContainerDisplay();
	// new ImagePanelContainerPresenter(display);

	// pane = new JScrollPane(display.asComponent());
	// pane.addMouseMotionListener(new MouseMotionListener(){
	//
	// @Override
	// public void mouseDragged(MouseEvent e) {
	// // TODO Auto-generated method stub
	//				
	// }
	//
	// @Override
	// public void mouseMoved(MouseEvent e) {
	// validateRoot();
	// }
	// });

	// this.add(pane);

	// pane.setPreferredSize(this.getSize());
	// display.asComponent().setBackground(Color.cyan);
	//
	// pane.setBackground(Color.red);
	//
	// pane.validate();
	// pane.setSize(this.getSize());

	// this.addComponentListener(new ComponentListener() {
	//
	// @Override
	// public void componentHidden(ComponentEvent arg0) {
	//
	// }
	//
	// @Override
	// public void componentMoved(ComponentEvent arg0) {
	// }
	//
	// @Override
	// public void componentResized(ComponentEvent arg0) {
	//				
	// pane.setPreferredSize(arg0.getComponent().getSize());
	// display.asComponent().validate();
	// pane.setSize(arg0.getComponent().getSize());
	// // pane.validate();
	// // validateRoot();
	// }
	//
	// @Override
	// public void componentShown(ComponentEvent arg0) {
	//
	// pane.setPreferredSize(arg0.getComponent().getSize());
	// display.asComponent().validate();
	// pane.setSize(arg0.getComponent().getSize());
	//
	// // pane.validate();
	// // validateRoot();
	// }
	// });
	// }
	//
	// public static void validateRoot() {
	// // if (pane==null) return;
	// // pane.repaint();
	// // pane.validate();
	// // pane.repaint();
	//
	// parent.repaint();
	// parent.validate();
	// parent.repaint();
	//	
	// }

	public static void main(String args[]) {
		// Create an instance of the test application
		ASKDataPresenter mainFrame = new ASKDataPresenter();
		mainFrame.init();
		mainFrame.setVisible(true);
	}
}
