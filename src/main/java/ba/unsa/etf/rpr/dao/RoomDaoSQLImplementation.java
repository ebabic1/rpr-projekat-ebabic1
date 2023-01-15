package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Room;
import org.jetbrains.annotations.Nullable;

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
        room.setRoomNumber(resultSet.getInt("roomNumber"));
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
        item.put("roomNumber",object.getRoomNumber());
        return item;
    }
    @Override
    public List<Room> searchByMaxPersons(int maxPersons) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE MaxPersons >= ?";
        return getRooms(maxPersons, roomList, query);
    }
    @Override
    public List<Room> searchByAvailable(int available) {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE available = ?";
        return getRooms(available, roomList, query);
    }
    @Override
    public List<Room> searchByNumber(int number){
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Rooms WHERE roomNumber = ?";
        return getRooms(number, roomList, query);
    }

    /**
     * Method for populating room list
     * @param available
     * @param roomList
     * @param query
     * @return
     */
    private List<Room> getRooms(int available, List<Room> roomList, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,available);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setRoomNumber(resultSet.getInt("roomNumber"));
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

    @Override
    public Room getByNumber(int roomNumber) throws SQLException {
        String query = "SELECT * FROM Rooms WHERE roomNumber = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,roomNumber);
        ResultSet resultSet = stmt.executeQuery();
        Room object = null;
        if (resultSet.next()){
            object = rowToObject(resultSet);
            resultSet.close();
        }
        if (object == null) throw new SQLException("Object not found!");
        return object;
    }
}
