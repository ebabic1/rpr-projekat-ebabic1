package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 Holds all hotel rooms
 @author Eldar Babić
 */
public class Room {
    private int roomId;
    private int maxPersons;

    @Override
    public String toString() {
        return "Rooms{" +
                "roomId=" + roomId +
                ", maxPersons=" + maxPersons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getRoomId() == room.getRoomId() && getMaxPersons() == room.getMaxPersons();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getMaxPersons());
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
