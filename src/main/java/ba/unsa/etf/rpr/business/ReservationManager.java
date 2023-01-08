package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ReservationException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

public class ReservationManager {
    public void validateDate(Reservation r) throws ReservationException {
        long daysBetween = ChronoUnit.DAYS.between(r.getArrivalDate().toLocalDate(),r.getLeaveDate().toLocalDate());
        if (daysBetween < 0) throw new ReservationException("Dates invalid, arrival date cannot be after leave date!");
        daysBetween = ChronoUnit.DAYS.between(r.getArrivalDate().toLocalDate(),LocalDate.now());
        if(daysBetween > 0) throw new ReservationException("Dates invalid, arrival date cannot be before today");
        daysBetween = ChronoUnit.DAYS.between(r.getLeaveDate().toLocalDate(),LocalDate.now());
        if(daysBetween > 0) throw new ReservationException("Dates invalid, leave date cannot be before today");
    }
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
            validateDate(r);
            Reservation test = DaoFactory.reservationDao().searchByUser(r.getUser().getId());
            Room reservedRoom = DaoFactory.roomDao().getById(r.getRoom().getId());
            if(reservedRoom.getAvailable() == 0)
                throw new ReservationException("Room is already reserved, try another!");
            else if (test != null)
                throw new ReservationException("User has already reserved a room!");
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
            validateDate(r);
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
