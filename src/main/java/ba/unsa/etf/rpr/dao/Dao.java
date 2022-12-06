package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Dao root interface
 * @author Eldar Babić
 */
public interface Dao<T> {
    /**
     * Method for getting items by their id
     * @param id - id of entity
     * @return entity with given id, else throws an exception
     */
    T getById(int id);

    /**
     * Adds entities to database
     * @param item - item to be added
     * @return saved item
     */
    T add(T item);

    /**
     * Updates database entities
     * @param item - item to be updated
     * @return updated version of item
     */
    T update(T item);

    /**
     * Deletes items by id
     * @param id - id of item to be deleted
     */
    void delete(int id);

    /**
     * Lists all entities from database
     * @return List of database entities
     */
    List<T> getAll();
}
