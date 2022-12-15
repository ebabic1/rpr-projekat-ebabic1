package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Room;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoomDaoSQLImplementation implements RoomDao {
    private Connection connection;

    public RoomDaoSQLImplementation() {
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
    public Room getById(int id) {
        String query = "SELECT * FROM Rooms WHERE roomId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("roomId"));
                room.setMaxPersons(resultSet.getInt("maxPersons"));
                resultSet.close();
                return room;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Room add(Room item) {
        String query = "INSERT INTO Rooms (maxPersons, description, available) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,  item.getMaxPersons());
            stmt.setString(2,  item.getDescription());
            stmt.setInt(3,item.getAvailable());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Room update(Room item) {
        String query = "UPDATE Reservations SET maxPersons = ? WHERE roomId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, item.getRoomId());
            stmt.setInt(2, item.getMaxPersons());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Rooms WHERE roomId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setRoomId(resultSet.getInt("roomId"));
                room.setMaxPersons(resultSet.getInt("maxPersons"));
                roomList.add(room);
            }
            resultSet.close();
            return roomList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Room> searchByMaxPersons(int maxPersons) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE MaxPersons >= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,maxPersons);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setRoomId(resultSet.getInt("roomId"));
                room.setMaxPersons(resultSet.getInt("maxPersons"));
                room.setDescription(resultSet.getString("description"));
                room.setAvailable(resultSet.getInt("available"));
                roomList.add(room);
            }
            resultSet.close();
            return roomList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Room> searchByAvailable(int available) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE available = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,available);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setRoomId(resultSet.getInt("roomId"));
                room.setMaxPersons(resultSet.getInt("maxPersons"));
                room.setDescription(resultSet.getString("description"));
                room.setAvailable(resultSet.getInt("available"));
                roomList.add(room);
            }
            resultSet.close();
            return roomList;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
