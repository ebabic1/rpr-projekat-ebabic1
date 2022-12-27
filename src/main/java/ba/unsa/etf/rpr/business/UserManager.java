package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.UserException;

import java.sql.SQLException;
import java.util.List;

public class UserManager {
    public void validate(String name) throws UserException{
        if (name == null ||name.trim().length() == 0) {
            throw new UserException("Name must be at least one character");
        }
    }

    public void delete(int id) throws UserException {
        try {
            DaoFactory.userDao().delete(id);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
    public User add(User u) throws UserException {
        try {
            DaoFactory.userDao().add(u);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
        return u;
    }
    public User update(User u) throws UserException{
        try {
            return DaoFactory.userDao().update(u);
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
    public List<User> getAll() throws UserException {
        try {
            return DaoFactory.userDao().getAll();
        }catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
    public User getById(int uId) throws UserException {
        try {
            return DaoFactory.userDao().getById(uId);
        }catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
}
