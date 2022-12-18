package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class UserDaoSQLImplementation extends AbstractDao<User> implements UserDao {
    private Connection connection;

    public UserDaoSQLImplementation(){
        super("Users");
    }


    @Override
    public User rowToObject(ResultSet resultSet) throws SQLException {
        User user = new User();
        user = makeNewUser(resultSet);
        return user;
    }

    @Override
    public Map<String, Object> objectToRow(User object) throws SQLException {
        Map<String, Object> item = new TreeMap<>();
        item.put("id",object.getId());
        item.put("firstName",object.getFirstName());
        item.put("lastName",object.getLastName());
        item.put("city",object.getCity());
        item.put("country",object.getCountry());
        item.put("email",object.getEmail());
        item.put("phone",object.getPhone());
        item.put("password",object.getPassword());
        item.put("admin",object.getAdmin());
        item.put("username",object.getUsername());
        return item;
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







    private User makeNewUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("userId"));
        user.setCity(resultSet.getString("city"));
        user.setCountry(resultSet.getString("country"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setAdmin(resultSet.getInt("admin"));
        user.setUsername(resultSet.getString("username"));
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
