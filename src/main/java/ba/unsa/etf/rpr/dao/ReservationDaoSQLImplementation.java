package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservationDaoSQLImplementation extends AbstractDao<Reservation> implements ReservationDao{
    private Connection connection;

    public ReservationDaoSQLImplementation() {
        super("Reservations");
    }
    @Override

    public Map<String, Object> ObjectToRow(Reservation object){
        Map<String, Object> row = new TreeMap<>();
        row.put("reservationId",object.getReservationId());
        row.put("arrivalDate",object.getArrivalDate());
        row.put("leaveDate",object.getLeaveDate());
        row.put("additionalInfo",object.getAdditionalInfo());
        row.put("roomId",object.getRoomId());
        row.put("guestId",object.getGuestId());
        return row;
    }
    @Override
    public Reservation RowToObject(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(resultSet.getInt("reservationId"));
        reservation.setArrivalDate(resultSet.getDate("arrivalDate"));
        reservation.setLeaveDate(resultSet.getDate("leaveDate"));
        reservation.setAdditionalInfo(resultSet.getString("additionalInfo"));
        reservation.setRoomId(resultSet.getInt("roomId"));
        reservation.setGuestId(resultSet.getInt("guestId"));
        return reservation;
    }



    @Override
    public Reservation add(Reservation item) {
        String query = "INSERT INTO Reservations (arrivalDate,leaveDate,additionalInfo,guestId,roomId) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1,  item.getArrivalDate());
            stmt.setDate(2, item.getLeaveDate());
            stmt.setString(3,item.getAdditionalInfo());
            stmt.setInt(4,item.getGuestId());
            stmt.setInt(5,item.getRoomId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Reservation update(Reservation item) {
        String query = "UPDATE Reservations SET arrivalDate = str_to_date(?,'%Y-%m-%d'),  leaveDate = str_to_date(?,'%Y-%m-%d'),  additionalInfo = ?,  guestId = ?,  roomId = ? WHERE reservationId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, item.getArrivalDate());
            stmt.setDate(2, item.getLeaveDate());
            stmt.setString(3,item.getAdditionalInfo());
            stmt.setInt(4,item.getGuestId());
            stmt.setInt(5,item.getRoomId());
            stmt.setInt(6,item.getReservationId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Reservations WHERE guestId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM Reservations";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                reservationList.add(RowToObject(resultSet));
            }
            resultSet.close();
            return reservationList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Reservation> searchByDateRange(java.sql.Date startDate, java.sql.Date endDate) {
        List<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM Reservations WHERE arrivalDate > str_to_date('"+startDate.toString()+"',%Y-%m-%d) AND leaveDate < str_to_date('"+endDate.toString()+"',%Y-%m-%d)" ;
        System.out.println(query);
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setReservationId(resultSet.getInt("reservationId"));
                reservation.setArrivalDate(resultSet.getDate("arrivalDate"));
                reservation.setLeaveDate(resultSet.getDate("leaveDate"));
                reservation.setAdditionalInfo(resultSet.getString("additionalInfo"));
                reservation.setRoomId(resultSet.getInt("roomId"));
                reservation.setGuestId(resultSet.getInt("guestId"));
                reservationList.add(reservation);
            }
            resultSet.close();
            return reservationList;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
