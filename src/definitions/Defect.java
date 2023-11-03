package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Defect {
	public SimpleStringProperty defectName;
	public Integer id;
	
	public Defect(Integer id, String defectName) {
		this.defectName = new SimpleStringProperty(defectName);
		this.id = id;
	}

	public String getDefectName() {
		return defectName.get();
	}

	public void setDefectName(String defectName) {
		this.defectName = new SimpleStringProperty(defectName);
	}
}