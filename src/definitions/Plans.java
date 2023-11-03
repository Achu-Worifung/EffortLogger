package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Plans {
	public SimpleStringProperty plansName;
	public Integer id;
	
	public Plans(Integer id, String plansName) {
		this.plansName = new SimpleStringProperty(plansName);
		this.id = id;
	}
	public String getPlansName() {
		return plansName.get();
	}

	public void setPlansName(String plansName) {
		this.plansName = new SimpleStringProperty(plansName);
	}
	
	
}