package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Break {
	public SimpleStringProperty breakName;
	public IntegerProperty id;
	
	public Break(Integer id, String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
		this.id = new SimpleIntegerProperty(id);
	}
	public String getBreakName() {
		return breakName.get();
	}

	public void setBreakName(String breakName) {
		this.breakName = new SimpleStringProperty(breakName);
	}
	public int getId()
	{
		  return id.get();
	}
	public void setId(int id) {
        this.id.set(id);
    }
}