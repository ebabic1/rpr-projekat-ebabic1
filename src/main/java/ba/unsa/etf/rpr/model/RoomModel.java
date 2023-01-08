package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Room;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model class for 2 way data binding with add/update form
 * @author Eldar Babić
 */
public class RoomModel {
    public SimpleDoubleProperty priceField = new SimpleDoubleProperty();
    public SimpleStringProperty descriptionField = new SimpleStringProperty();
    public SimpleIntegerProperty maxPersonsField = new SimpleIntegerProperty();
    public SimpleIntegerProperty roomNumberField = new SimpleIntegerProperty();

    public int getRoomNumberField() {
        return roomNumberField.get();
    }

    public SimpleIntegerProperty roomNumberFieldProperty() {
        return roomNumberField;
    }

    public void setRoomNumberField(int roomNumberField) {
        this.roomNumberField.set(roomNumberField);
    }

    public void fromRoom(Room r) {
        this.priceField.set(r.getPrice());
        this.descriptionField.set(r.getDescription());
        this.maxPersonsField.set(r.getMaxPersons());
        this.roomNumberField.set(r.getRoomNumber());
    }
    public Room toRoom() {
        Room r = new Room();
        r.setPrice(this.priceField.getValue());
        r.setDescription(this.descriptionField.getValue());
        r.setMaxPersons(this.maxPersonsField.getValue());
        r.setRoomNumber(this.roomNumberField.getValue());
        return r;
    }

    public double getPriceField() {
        return priceField.get();
    }

    public SimpleDoubleProperty priceFieldProperty() {
        return priceField;
    }

    public void setPriceField(double priceField) {
        this.priceField.set(priceField);
    }

    public String getDescriptionField() {
        return descriptionField.get();
    }

    public SimpleStringProperty descriptionFieldProperty() {
        return descriptionField;
    }

    public void setDescriptionField(String descriptionField) {
        this.descriptionField.set(descriptionField);
    }

    public int getMaxPersonsField() {
        return maxPersonsField.get();
    }

    public SimpleIntegerProperty maxPersonsFieldProperty() {
        return maxPersonsField;
    }

    public void setMaxPersonsField(int maxPersonsField) {
        this.maxPersonsField.set(maxPersonsField);
    }
}
