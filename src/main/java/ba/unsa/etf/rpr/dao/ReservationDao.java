package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.sql.Date;
import java.util.List;

/**
 * Dao interface for reservation bean
 */
public interface ReservationDao extends Dao<Reservation> {
    /**
     * Lists out all reservations made duringa a date range
     * @param startDate - starting date
     * @param endDate - ending date
     * @return List of dates
     */
    List<Reservation> searchByDateRange(Date startDate, Date endDate);
}
