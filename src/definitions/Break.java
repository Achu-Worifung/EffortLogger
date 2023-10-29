package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Break {
	private SimpleStringProperty breakName;
	
	public Break(String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
	}
	public String getBreakName() {
		return breakName.get();
	}

	public void setBreakName(String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
	}
}