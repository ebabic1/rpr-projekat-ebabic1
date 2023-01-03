package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.ReservationException;

import java.sql.SQLException;
import java.util.List;

public class ReservationManager {
    public void delete(int id) throws ReservationException {
        try {
            Room reservedRoom = DaoFactory.roomDao().getById(DaoFactory.reservationDao().getById(id).getRoom().getId());
            reservedRoom.setAvailable(1);
            DaoFactory.roomDao().update(reservedRoom);
            DaoFactory.reservationDao().delete(id);
        } catch (SQLException e) {
            throw new ReservationException(e.getMessage());
        }
    }
    public Reservation add(Reservation r) throws ReservationException {
        try {
            Room reservedRoom = DaoFactory.roomDao().getById(r.getRoom().getId());
            if(reservedRoom.getAvailable() == 0)
                throw new ReservationException("Room is already reserved, try another");
            else {
                reservedRoom.setAvailable(0);
                DaoFactory.roomDao().update(reservedRoom);
                DaoFactory.reservationDao().add(r);
            }
        } catch (SQLException e) {
            throw new ReservationException(e.getMessage());
        }
        return r;
    }
    public Reservation update(Reservation r) throws ReservationException {
        try {
            return DaoFactory.reservationDao().update(r);
        } catch (SQLException e) {
            throw new ReservationException(e.getMessage());
        }
    }
    public List<Reservation> getAll() throws ReservationException {
        try {
            return DaoFactory.reservationDao().getAll();
        } catch (SQLException e) {
            throw new ReservationException(e.getMessage());
        }
    }
    public Reservation getById(int rId) throws ReservationException {
        try {
            return DaoFactory.reservationDao().getById(rId);
        } catch (SQLException e) {
            throw new ReservationException(e.getMessage());
        }
    }
}
