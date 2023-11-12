package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category {
	public SimpleStringProperty categoryName;
	public IntegerProperty id;
	
	public Category(int id,String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
		  this.id = new SimpleIntegerProperty(id);
	}

	public String getCategoryName() {
		return categoryName.get();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
	}
	public int getId()
	{
		  return id.get();
	}
	public void setId(int id) {
        this.id.set(id);
    }
}