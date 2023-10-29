package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Plans {
	private SimpleStringProperty plansName;
	
	public Plans(String plansName) {
		this.plansName = new SimpleStringProperty(plansName);
	}
	public String getPlansName() {
		return plansName.get();
	}

	public void setPlansName(String plansName) {
		this.plansName = new SimpleStringProperty(plansName);
	}
}