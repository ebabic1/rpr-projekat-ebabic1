package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;

import java.util.List;

/**
 * Dao interface for guest bean
 */
public interface GuestDao extends Dao<Guest> {
    /**
     * Lists all guests from given city
     * @param cityName - city name
     * @return list of guests
     */
    List<Guest> searchByCity(String cityName);
    /**
     * Lists all guests from given country
     * @param countryName - city name
     * @return list of guests
     */
    List<Guest> searchByCountry(String countryName);
}
