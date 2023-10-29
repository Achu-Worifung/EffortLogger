package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Defect {
	private SimpleStringProperty defectName;
	
	public Defect(String defectName) {
		this.defectName = new SimpleStringProperty(defectName);
	}

	public String getDefectName() {
		return defectName.get();
	}

	public void setDefectName(String defectName) {
		this.defectName = new SimpleStringProperty(defectName);
	}
}