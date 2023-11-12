package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Life {
	public SimpleStringProperty lifeName;
	public IntegerProperty defaultDefect, defaultEC, id;
	
	public Life(Integer id, String lifeName, Integer defaultDefect, Integer defaultEC) {
		this.lifeName = new SimpleStringProperty(lifeName);
		this.defaultDefect = new SimpleIntegerProperty(defaultDefect);
		this.defaultEC = new SimpleIntegerProperty(defaultEC);
		this.id = new SimpleIntegerProperty(id);
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

	public int getDefaultDefect() {
		return defaultDefect.get();
	}

	public void setDefaultDefect(Integer defaultDefect) {
		this.defaultDefect.set(getDefaultDefect());;
	}

	public int getDefaultEC() {
		return defaultEC.get();
	}

	public void setDefaultEC(int defaultEC) {
		this.defaultEC.set(defaultEC);;
	}
	public int getId()
	{
		  return id.get();
	}
	public void setId(int id) {
        this.id.set(id);
    }
	
	
}