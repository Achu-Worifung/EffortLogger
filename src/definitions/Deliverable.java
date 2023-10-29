package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Deliverable {
	private SimpleStringProperty deliverableName;
	
	public Deliverable(String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
	}

	public String getDeliverableName() {
		return deliverableName.get();
	}

	public void setDeliverableName(String deliverableName) {
		this.deliverableName = new SimpleStringProperty(deliverableName);
	}
}