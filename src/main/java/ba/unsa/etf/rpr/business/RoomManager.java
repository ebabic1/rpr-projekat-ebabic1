package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.RoomException;

import java.sql.SQLException;
import java.util.List;

public class RoomManager {

    public void delete(int id) throws  RoomException {
        try {
            DaoFactory.roomDao().delete(id);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }
    public Room add(Room r) throws RoomException {
        try {
            Room addedRoom = DaoFactory.roomDao().add(r);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
        return r;
    }
    public Room update(Room r) throws RoomException {
        try {
            return DaoFactory.roomDao().update(r);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }
    public List<Room> getAll() throws RoomException {
        try {
            return DaoFactory.roomDao().getAll();
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }
    public Room getById(int id) throws RoomException {
        try {
            return DaoFactory.roomDao().getById(id);
        } catch (SQLException e) {
            throw new RoomException(e.getMessage());
        }
    }
}

