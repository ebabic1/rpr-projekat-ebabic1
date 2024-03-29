package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao interface for reservation bean
 */
public interface ReservationDao extends Dao<Reservation> {
    /**
     * Lists out all reservations made during a date range
     * @param startDate - starting date
     * @param endDate - ending date
     * @return List of dates
     */
    List<Reservation> searchByDateRange(Date startDate, Date endDate);

    /**
     * Returns reservation made by particular user
     * @param id user ID
     * @return Reservatoin
     * @throws SQLException when user is not found
     */
    Reservation searchByUser(int id) throws SQLException;
}
