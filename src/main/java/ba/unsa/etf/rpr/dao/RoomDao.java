package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;

import java.util.List;

/**
 * Dao interface for Room bean
 */
public interface RoomDao extends Dao<Room> {
    /**
     * Returns list of rooms of given capacity
     * @param maxPersons - room capacity
     * @return list of rooms
     */
    List<Room> searchByMaxPersons(int maxPersons);
}
