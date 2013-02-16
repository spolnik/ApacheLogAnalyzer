package ask.gui.imageForm;

import java.awt.event.ActionListener;
import java.util.Date;

import ask.gui.Display;

public interface ImageForm {
	
	void showForm(/*??*/);
	public interface ImageFormDisplay extends Display{
		void addCommitFormListener(ActionListener listener);
		
		void setMasterTypes(String[] types);
		void setTypes(String masterType, String[] types);
		
		Date getDateFrom();
		String getMasterType();
		Date getDateTo();
		int getLimit();
		String getType();
		void popup();

		void setProviders(String[] types);

		String getProvider();
	}
}
