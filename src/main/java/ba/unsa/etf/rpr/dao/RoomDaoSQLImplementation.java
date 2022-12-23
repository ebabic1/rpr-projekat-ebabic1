package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Room;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class RoomDaoSQLImplementation extends AbstractDao<Room> implements RoomDao {

    public RoomDaoSQLImplementation() {
        super("Rooms");
    }

    @Override
    public Room rowToObject(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("id"));
        room.setMaxPersons(resultSet.getInt("maxPersons"));
        room.setDescription(resultSet.getString("description"));
        room.setAvailable(resultSet.getInt("available"));
        room.setPrice(resultSet.getDouble("price"));
        return room;
    }

    @Override
    public Map<String, Object> objectToRow(Room object) throws SQLException {
        Map<String,Object> item = new TreeMap<>();
        item.put("id",object.getId());
        item.put("maxPersons",object.getMaxPersons());
        item.put("description",object.getDescription());
        item.put("available",object.getAvailable());
        item.put("price",object.getPrice());
        return item;
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
                room.setId(resultSet.getInt("roomId"));
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
                room.setId(resultSet.getInt("roomId"));
                room.setMaxPersons(resultSet.getInt("maxPersons"));
                room.setDescription(resultSet.getString("description"));
                room.setAvailable(resultSet.getInt("available"));
                roomList.add(room);
            }
            resultSet.close();
            return roomList;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
