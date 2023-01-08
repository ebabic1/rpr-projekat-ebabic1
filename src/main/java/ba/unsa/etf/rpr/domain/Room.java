package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DaoFactory;

import java.sql.SQLException;
import java.util.Objects;

/**
 Holds all hotel rooms
 @author Eldar Babić
 */
public class Room implements IDable{
    private int Id;
    private int maxPersons;
    private String description;
    private int available;
    private double price;
    private int roomNumber;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getId() == room.getId() && getMaxPersons() == room.getMaxPersons() && getAvailable() == room.getAvailable() && Double.compare(room.getPrice(), getPrice()) == 0 && roomNumber == room.roomNumber && Objects.equals(getDescription(), room.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMaxPersons(), getDescription(), getAvailable(), getPrice(), roomNumber);
    }

    @Override
    public String toString() {
        try {
            return String.valueOf(DaoFactory.roomDao().getById(Id).getRoomNumber());
        } catch (SQLException e) {
            return new String();
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getId() {
        return Id;
    }

    public void setId(int roomId) {
        this.Id = roomId;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
