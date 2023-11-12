package definitions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plans {
    public StringProperty plansName;
    public IntegerProperty id;

    public Plans(int id, String plansName) {
        this.plansName = new SimpleStringProperty(plansName);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getPlansName() {
        return plansName.get();
    }

    public int getId() {
        return id.get();
    }

    public void setPlansName(String plansName) {
        this.plansName.set(plansName);
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
