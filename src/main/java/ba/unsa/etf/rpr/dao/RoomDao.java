package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rooms;

import java.util.List;

/**
 * Dao interface for Room bean
 */
public interface RoomDao<Rooms> extends Dao<Rooms> {
    /**
     * Returns list of rooms of given capacity
     * @param maxPersons - room capacity
     * @return list of rooms
     */
    List<Rooms> searchByMaxPersons(int maxPersons);
}
