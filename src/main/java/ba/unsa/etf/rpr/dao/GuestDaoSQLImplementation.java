package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
                Guest g = makeNewGuest(resultSet);
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
    public Guest add(Guest item) {
        String query = "INSERT INTO Guests (firstName,lastName,city,country,email,phone) VALUES (?,?,?,?,NULL,?)";
        return getGuest(item, query);
    }

    private Guest getGuest(Guest item, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getCity());
            stmt.setString(4,item.getCountry());
            stmt.setString(5,item.getPhone());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Guest update(Guest item) {
        String query = "UPDATE Guests SET guestId = ?, SET firstName = ?, SET lastName = ?, SET city = ?, SET country = ?, SET email = ?, SET phone = ?";
        return getGuest(item, query);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Guests WHERE guestId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Guest> getAll() {
        List<Guest> guestList = new ArrayList<>();
        String query = "SELECT * FROM Guests";
        return getGuests(guestList, query);

    }

    private Guest makeNewGuest(ResultSet resultSet) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestId(resultSet.getInt("guestId"));
        guest.setCity(resultSet.getString("city"));
        guest.setCountry(resultSet.getString("country"));
        guest.setEmail(resultSet.getString("email"));
        guest.setPhone(resultSet.getString("phone"));
        guest.setFirstName(resultSet.getString("firstName"));
        guest.setLastName(resultSet.getString("lastName"));
        return guest;
    }

    @Override
    public List<Guest> searchByCity(String cityName)
    {
        List<Guest> guestList = new ArrayList<>();
        String query = "SELECT * FROM Guests WHERE city = 'cityName'";
        return getGuests(guestList, query);
    }

    @Override
    public List<Guest> searchByCountry(String countryName) {
        List<Guest> guestList = new ArrayList<>();
        String query = "SELECT * FROM Guests WHERE country = 'countryName'";
        return getGuests(guestList, query);
    }

    private List<Guest> getGuests(List<Guest> guestList, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Guest guest;
                guestList.add(makeNewGuest(resultSet));
            }
            resultSet.close();
            return guestList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
