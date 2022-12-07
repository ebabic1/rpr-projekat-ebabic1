package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.sql.*;
import java.util.Properties;

public class GuestDaoSQLImplementation implements GuestDao{
    private Connection connection;

    public GuestDaoSQLImplementation(){
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
    public Guest getById(int id) {
        String query = "SELECT * FROM Guests WHERE guestId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
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
        String query = "INSERT INTO Guests (firstName,lastName,city,country,email,phone) VALUES (?,?,?,?,NULL,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getCity());
            stmt.setString(4,item.getCountry());
            stmt.setString(5,item.getPhone());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
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
