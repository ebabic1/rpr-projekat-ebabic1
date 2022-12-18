package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 Holds all hotel rooms
 @author Eldar Babić
 */
public class Room {
    private int roomId;
    private int maxPersons;
    private String description;
    private int available;
    private double price;

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", maxPersons=" + maxPersons +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getRoomId() == room.getRoomId() && getMaxPersons() == room.getMaxPersons() && getAvailable() == room.getAvailable() && getPrice() == room.getPrice() && Objects.equals(getDescription(), room.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getMaxPersons(), getDescription(), getAvailable(), getPrice());
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }
}
