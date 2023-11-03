package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Category {
	public SimpleStringProperty categoryName;
	public Integer id;
	
	public Category(Integer id,String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName.get();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
	}
}