package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Defect {
	public StringProperty defectName;
	public IntegerProperty id;
	
	public Defect(Integer id, String defectName) {
		this.defectName = new SimpleStringProperty(defectName);
		this.id = new SimpleIntegerProperty(id);
	}

	public String getDefectName() {
		return defectName.get();
	}

	public void setDefectName(String defectName) {
		this.defectName.set(defectName);
	}
	public int getId()
	{
		  return id.get();
	}
	public void setId(int id) {
        this.id.set(id);
    }
}