package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Deliverable {
	private SimpleStringProperty deliverableName;
	public Integer id;
	
	public Deliverable(Integer id, String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
		this.id = id;
	}

	public String getDeliverableName() {
		return deliverableName.get();
	}

	public void setDeliverableName(String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
	}
}