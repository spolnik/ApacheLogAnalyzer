package ask.gui.imageForm;

import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import sun.awt.VerticalBagLayout;

public class ImageFormDisplay implements
		ask.gui.imageForm.ImageForm.ImageFormDisplay {

	private JPanel panel = new JPanel();

	private JComboBox masterTypes = new JComboBox();

	private Set<String> msTypes = new HashSet<String>();

	private Map<String, Set<String>> msTypesToTypes = new HashMap<String, Set<String>>();

	private JComboBox providers= new JComboBox();
	private JComboBox types= new JComboBox();
	
	private Button getButton = new Button("fetch data");
	
	private JSpinner limit = new JSpinner();
	
	private JSpinner dateFrom = new JSpinner();
	private JSpinner dateTo = new JSpinner();

	private SpinnerDateModel dateFromModel;

	private SpinnerDateModel dateToModel;
	
	public ImageFormDisplay() {
		masterTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				types.removeAllItems();
				if (msTypesToTypes.containsKey((String)masterTypes.getSelectedItem()))
				for (String type : msTypesToTypes.get((String)masterTypes.getSelectedItem())) {
					types.addItem(type);
				}
			}
			
		});
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(1);
		model.setValue(1);
		limit.setModel(model);
		
		dateFromModel = new SpinnerDateModel();
		dateFromModel.setEnd(new Date());
		dateFrom.setModel(dateFromModel);
		
		dateToModel = new SpinnerDateModel();
		dateToModel.setEnd(new Date());
		dateTo.setModel(dateToModel);
		
		VerticalBagLayout layout = new VerticalBagLayout();
		panel.setLayout(layout);
//		getButton.setText();
		
		panel.add(providers);
		panel.add(masterTypes);
		panel.add(types);
		panel.add(limit);
		panel.add(dateFrom);
		panel.add(dateTo);
		panel.add(getButton);
	}
	
	@Override
	public void addCommitFormListener(ActionListener listener) {
		getButton.addActionListener(listener);
	}

	@Override
	public Date getDateFrom() {
		// TODO Auto-generated method stub
		return dateFromModel.getDate();
	}

	@Override
	public Date getDateTo() {
		// TODO Auto-generated method stub
		return dateToModel.getDate();
	}

	@Override
	public int getLimit() {
		return (Integer) limit.getValue();
	}

	@Override
	public String getMasterType() {
		return (String) masterTypes.getSelectedItem();
	}

	@Override
	public String getType() {
		return (String) types.getSelectedItem();
	}

	@Override
	public String getProvider() {
		return (String) providers.getSelectedItem();
	}
	
	@Override
	public void popup() {
		JFrame frame = new JFrame();
		JPanel tmp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		frame.add(tmp);
		tmp.add(panel);
		frame.setSize(250, 250);
		frame.setLocation(300,200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public Component asComponent() {
		return panel;
	}

	
	@Override
	public void setProviders(String[] types) {
		providers.removeAllItems();
		for (String type : types) {
			providers.addItem(type);
		}
	}
	
	@Override
	public void setMasterTypes(String[] types) {
		this.msTypes.addAll(Arrays.asList(types));
		masterTypes.removeAllItems();
		for (String type : msTypes) {
			masterTypes.addItem(type);
		}
	}

	@Override
	public void setTypes(String masterType, String[] types) {
		if (msTypes.contains(masterType)) {
			Set<String> typesSet = new HashSet<String>(Arrays.asList(types));
			msTypesToTypes.put(masterType, typesSet);
//			for (String type : typesSet) {
//				this.types.addItem(type);
//			}
		} else
			throw new IllegalArgumentException("no type " + masterType
					+ " exists");
	}

}
