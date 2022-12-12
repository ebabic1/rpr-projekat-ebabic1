package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.util.List;

/**
 * Dao interface for guest bean
 */
public interface UserDao extends Dao<User> {
    /**
     * Lists all guests from given city
     * @param cityName - city name
     * @return list of guests
     */
    List<User> searchByCity(String cityName);
    /**
     * Lists all guests from given country
     * @param countryName - city name
     * @return list of guests
     */
    List<User> searchByCountry(String countryName);
}
