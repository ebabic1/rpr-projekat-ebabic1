package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.UserException;

import java.sql.SQLException;
import java.util.List;

public class UserManager {
    /**
     * Validates user's name
     * @param u
     * @throws UserException
     */
    public void validate(User u) throws UserException{
        if (u.getFirstName() == null || u.getFirstName().trim().length() == 0 || u.getLastName().trim().length() == 0) {
            throw new UserException("Name must be at least one character");
        }
    }

    /**
     * Deletes user given by parameter from database
     * @param id
     * @throws UserException
     */
    public void delete(int id) throws UserException {
        try {
            DaoFactory.userDao().delete(id);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }

    /**
     * Adds user given by parameter to database
     * @param u
     * @return
     * @throws UserException
     */
    public User add(User u) throws UserException {
        try {
            validate(u);
            DaoFactory.userDao().add(u);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
        return u;
    }

    /**
     * Updates user given by parameter
     * @param u
     * @return
     * @throws UserException
     */
    public User update(User u) throws UserException{
        try {
            return DaoFactory.userDao().update(u);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }

    /**
     * Gets all users from database
     * @return List of users
     * @throws UserException
     */
    public List<User> getAll() throws UserException {
        try {
            return DaoFactory.userDao().getAll();
        }catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }

    /**
     * Gets user from database by ID
     * @param uId
     * @return
     * @throws UserException
     */
    public User getById(int uId) throws UserException {
        try {
            return DaoFactory.userDao().getById(uId);
        }catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
}
