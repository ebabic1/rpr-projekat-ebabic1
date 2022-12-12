package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Properties;

public class UserDaoSQLImplementation implements UserDao {
    private Connection connection;

    public UserDaoSQLImplementation(){
        try {
            final Properties login = new Properties();
            login.load(new FileInputStream("src/main/java/ba/unsa/etf/rpr/login.properties"));
            connection = DriverManager.getConnection(login.getProperty("connection.string"), login.getProperty("username"), login.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public User getById(int id) {
        String query = "SELECT * FROM Users WHERE userId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                User g = makeNewUser(resultSet);
                resultSet.close();
                return g;
            }
            return null;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User add(User item) {
        String query = "INSERT INTO Users (firstName,lastName,city,country,email,phone,password) VALUES (?,?,?,?,NULL,?,?)";
        return getUser(item, query);
    }

    private User getUser(User item, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getCity());
            stmt.setString(4,item.getCountry());
            stmt.setString(5,item.getPhone());
            stmt.setString(6,item.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public User update(User item) {
        String query = "UPDATE Users  SET firstName = ?, SET lastName = ?, SET city = ?, SET country = ?, SET email = ?, SET phone = ?, SET password = ?, SET roleId = ? WHERE userId = ";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getCity());
            stmt.setString(4,item.getCountry());
            stmt.setString(5,item.getEmail());
            stmt.setString(6,item.getPhone());
            stmt.setString(7,item.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Users WHERE userId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users";
        return getUsers(userList, query);

    }

    private User makeNewUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setCity(resultSet.getString("city"));
        user.setCountry(resultSet.getString("country"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        return user;
    }

    @Override
    public List<User> searchByCity(String cityName)
    {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE city = 'cityName'";
        return getUsers(userList, query);
    }

    @Override
    public List<User> searchByCountry(String countryName) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE country = 'countryName'";
        return getUsers(userList, query);
    }

    private List<User> getUsers(List<User> userList, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                User user;
                userList.add(makeNewUser(resultSet));
            }
            resultSet.close();
            return userList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
