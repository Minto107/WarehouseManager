package mainWindow;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Manager implements Serializable {

    public String name;
    public String category;
    public int quantity;

    public Manager(String name, int quantity, String category) {
        setName(name);
        setQuantity(quantity);
        setCategory(category);
    }


    @Override
    public String toString() {
        return getName() + " " + getQuantity() + " " + getCategory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public IntegerProperty quantityProperty() {
        return new SimpleIntegerProperty(quantity);
    }

    public StringProperty categoryProperty() {
        return new SimpleStringProperty(category);
    }
}
