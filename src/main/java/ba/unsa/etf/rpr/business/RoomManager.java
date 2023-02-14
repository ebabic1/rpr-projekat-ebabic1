package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.RoomException;

import java.sql.SQLException;
import java.util.List;

public class RoomManager {
    /**
     * Deletes room from database by ID
     * @param id roomId
     * @throws RoomException
     */
    public void delete(int id) throws  RoomException {
        try {
            DaoFactory.roomDao().delete(id);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }

    /**
     * Adds room given by parameter to database
     * @param r Room object
     * @return Added room
     * @throws RoomException
     */
    public Room add(Room r) throws RoomException {
        try {
            Room addedRoom = DaoFactory.roomDao().add(r);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
        return r;
    }

    /**
     * Updates room given by parameter
     * @param r Room
     * @return
     * @throws RoomException
     */
    public Room update(Room r) throws RoomException {
        try {
            return DaoFactory.roomDao().update(r);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }

    /**
     * Gets all rooms from database
     * @return List of rooms
     * @throws RoomException
     */
    public List<Room> getAll() throws RoomException {
        try {
            return DaoFactory.roomDao().getAll();
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }

    /**
     * Gets room by ID from database
     * @param id roomId
     * @return Room
     * @throws RoomException
     */
    public Room getById(int id) throws RoomException {
        try {
            return DaoFactory.roomDao().getById(id);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }
}

