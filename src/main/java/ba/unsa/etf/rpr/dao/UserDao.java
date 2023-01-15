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
     * Lists all users from given country
     * @param countryName - city name
     * @return list of user objects
     */
    List<User> searchByCountry(String countryName);
    /**
     * Lists all users with given name
     * @param name - User name
     * @return list of user objects
     */
    List<User> searchByName(String name);

    /**
     * Lists all users with given username
     * @param text username
     * @return user object
     */
    User getByUsername(String text);
}
