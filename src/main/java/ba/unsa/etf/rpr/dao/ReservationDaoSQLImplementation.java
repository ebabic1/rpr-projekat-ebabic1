package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReservationDaoSQLImplementation implements ReservationDao{
    private Connection connection;

    public ReservationDaoSQLImplementation() {
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
    public Reservation getById(int id) {String query = "SELECT * FROM Reservations WHERE reservationId = ?";
        try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()){
            Reservation reservation = setReservation(resultSet);
            resultSet.close();
            return reservation;
        }
        return null;

    }catch(SQLException e) {
        e.printStackTrace();
    }
        return null;
}

    private Reservation setReservation(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(resultSet.getInt("reservationId"));
        reservation.setArrivalDate(resultSet.getDate("arrivalDate"));
        reservation.setLeaveDate(resultSet.getDate("leaveDate"));
        reservation.setAdditionalInfo(resultSet.getString("additionalInfo"));
        reservation.setRoomId(resultSet.getInt("roomId"));
        reservation.setGuestId(resultSet.getInt("guestId"));
        reservation.setPaymentAmount(resultSet.getDouble("paymentAmount"));
        return reservation;
    }

    @Override
    public Reservation add(Reservation item) {
        String query = "INSERT INTO Reservations (arrivalDate,leaveDate,paymentAmount,additionalInfo,guestId,roomId) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1,  item.getArrivalDate());
            stmt.setDate(2, item.getLeaveDate());
            stmt.setDouble(3,item.getPaymentAmount());
            stmt.setString(4,item.getAdditionalInfo());
            stmt.setInt(5,item.getGuestId());
            stmt.setInt(6,item.getRoomId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Reservation update(Reservation item) {
        String query = "UPDATE Reservations SET arrivalDate = str_to_date(?,'%Y-%m-%d'),  leaveDate = str_to_date(?,'%Y-%m-%d'),  paymentAmount = ?,  additionalInfo = ?,  guestId = ?,  roomId = ? WHERE reservationId = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDate(1, item.getArrivalDate());
            stmt.setDate(2, item.getLeaveDate());
            stmt.setDouble(3,item.getPaymentAmount());
            stmt.setString(4,item.getAdditionalInfo());
            stmt.setInt(5,item.getGuestId());
            stmt.setInt(6,item.getRoomId());
            stmt.setInt(7,item.getReservationId());
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
                reservationList.add(setReservation(resultSet));
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
                reservation.setPaymentAmount(resultSet.getDouble("paymentAmount"));
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
