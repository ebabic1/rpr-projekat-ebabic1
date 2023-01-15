package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;

import java.sql.SQLException;
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

    /**
     * Returns list of available/unavailable rooms
     * @param available value 1/0
     * @return list of rooms
     */
    List<Room> searchByAvailable(int available);

    /**
     * Returns particular room identified by room number
     * @param roomNumber room number
     * @return room
     * @throws SQLException when room is not found
     */
    Room getByNumber(int roomNumber) throws SQLException;

    /**
     * Returns list of rooms with given room number for populating table
     * @param number
     * @return list of rooms
     */
    public List<Room> searchByNumber(int number);
}
