package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Deliverable {
	private SimpleStringProperty deliverableName;
	public IntegerProperty id;
	
	public Deliverable(Integer id, String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
		this.id = new SimpleIntegerProperty(id);
	}

	public String getDeliverableName() {
		return deliverableName.get();
	}

	public void setDeliverableName(String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
	}
	public int getId()
	{
		  return id.get();
	}
	public void setId(int id) {
        this.id.set(id);
    }
}