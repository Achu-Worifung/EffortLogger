package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Break {
	public SimpleStringProperty breakName;
	public Integer id;
	
	public Break(Integer id, String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
		this.id = id;
	}
	public String getBreakName() {
		return breakName.get();
	}

	public void setBreakName(String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
	}
}