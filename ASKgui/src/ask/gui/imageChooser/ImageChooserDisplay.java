package ask.gui.imageChooser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class ImageChooserDisplay implements
		ask.gui.imageChooser.ImageChooser.ImageChooserDisplay {

	private JPanel container = new JPanel();
	private JSpinner spinner = new JSpinner();
	private JButton add = new JButton("[ + ]");
	private JButton remove = new JButton("[ - ]");
	public JTextField host = new JTextField("localhost                      ");
	public JTextField port = new JTextField("8732                  ");
	private SpinnerNumberModel model = new SpinnerNumberModel();

	public ImageChooserDisplay() {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		container.setLayout(layout);
		container.add(host);
		container.add(port);
		container.add(add);
		container.add(spinner);
		container.add(remove);
		model.setMaximum(0);
		model.setMinimum(0);
		spinner.setModel(model);
	}

	@Override
	public Integer getCurrentImage() {
		return (Integer) spinner.getValue();
	}

	@Override
	public void setAddHandler(ActionListener listener) {
		add.addActionListener(listener);
	}

	@Override
	public void setRemoveHandler(ActionListener listener) {
		remove.addActionListener(listener);
	}

	@Override
	public void addChangeImageHandler(ChangeListener listener) {
		spinner.addChangeListener(listener);
	}

	@Override
	public Component addConponnet() {
		return container;
	}

	@Override
	public void setImageCount(int i) {
		// if (model.getMaximum().compareTo(i)>0) {
		model.setValue(i);
		// }
		model.setMaximum(i);

	}

	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return host.getText().trim();
	}

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return port.getText().trim();
	}

}
