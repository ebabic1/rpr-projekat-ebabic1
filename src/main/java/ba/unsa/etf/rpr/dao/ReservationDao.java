package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservations;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for reservation bean
 */
public interface ReservationDao extends Dao<Reservations> {
    /**
     * Lists out all reservations made duringa a date range
     * @param startDate - starting date
     * @param endDate - ending date
     * @return List of dates
     */
    List<Reservations> searchByDateRange(Date startDate, Date endDate);
}
