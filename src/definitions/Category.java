package definitions;

import javafx.beans.property.SimpleStringProperty;

public class Category {
	private SimpleStringProperty categoryName;
	
	public Category(String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
	}

	public String getCategoryName() {
		return categoryName.get();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = new SimpleStringProperty(categoryName);
	}
}