package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Life {
	public SimpleStringProperty lifeName;
	public Integer defaultDefect, defaultEC, id;
	
	public Life(Integer id, String lifeName, Integer defaultDefect, Integer defaultEC) {
		this.lifeName = new SimpleStringProperty(lifeName);
		this.defaultDefect = defaultDefect;
		this.defaultEC = defaultEC;
		this.id = id;
	}
	
	public Life(String lifeName) {
		this.lifeName = new SimpleStringProperty(lifeName);
	}

	public String getLifeName() {
		return lifeName.get();
	}

	public void setLifeName(String lifeName) {
		this.lifeName = new SimpleStringProperty(lifeName);
	}

	public Integer getDefaultDefect() {
		return defaultDefect;
	}

	public void setDefaultDefect(Integer defaultDefect) {
		this.defaultDefect = defaultDefect;
	}

	public Integer getDefaultEC() {
		return defaultEC;
	}

	public void setDefaultEC(Integer defaultEC) {
		this.defaultEC = defaultEC;
	}
	
	
}