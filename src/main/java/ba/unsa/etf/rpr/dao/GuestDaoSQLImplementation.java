package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;

import java.util.List;
import java.sql.*;
import java.util.Properties;

public class GuestDaoSQLImplementation implements GuestDao{
    private Connection connection;

    public GuestDaoSQLImplementation(){
        try {
            final Properties login = new Properties();
            connection = DriverManager.getConnection(login.getProperty("connection_string"), login.getProperty("username"), login.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Guest getById(int id) {
        String query = "SELECT * FROM Guests WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Guest guest = new Guest();
                guest.setGuestId(id);
                guest.setCity(resultSet.getString("city"));
                guest.setCountry(resultSet.getString("country"));
                guest.setEmail(resultSet.getString("email"));
                guest.setPhone(resultSet.getString("phone"));
                guest.setFirstName(resultSet.getString("firstName"));
                guest.setLastName(resultSet.getString("lastName"));
                resultSet.close();
                return guest;
            }
            return null;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Guest add(Guest item) {
        return null;
    }

    @Override
    public Guest update(Guest item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Guest> getAll() {
        return null;
    }

    @Override
    public List<Guest> searchByCity(String cityName) {
        return null;
    }

    @Override
    public List<Guest> searchByCountry(String countryName) {
        return null;
    }
}
