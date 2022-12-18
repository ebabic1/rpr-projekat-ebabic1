package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;

import java.sql.*;
import java.util.*;

public class ReservationDaoSQLImplementation extends AbstractDao<Reservation> implements ReservationDao{
    private Connection connection;

    public ReservationDaoSQLImplementation() {super("Reservations");
    }
    @Override

    public Map<String, Object> objectToRow(Reservation object){
        Map<String, Object> row = new TreeMap<>();
        row.put("reservationId",object.getId());
        row.put("arrivalDate",object.getArrivalDate());
        row.put("leaveDate",object.getLeaveDate());
        row.put("additionalInfo",object.getAdditionalInfo());
        row.put("roomId",object.getRoomId());
        row.put("guestId",object.getGuestId());
        return row;
    }
    @Override
    public Reservation rowToObject(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("reservationId"));
        reservation.setArrivalDate(resultSet.getDate("arrivalDate"));
        reservation.setLeaveDate(resultSet.getDate("leaveDate"));
        reservation.setAdditionalInfo(resultSet.getString("additionalInfo"));
        reservation.setRoomId(resultSet.getInt("roomId"));
        reservation.setGuestId(resultSet.getInt("guestId"));
        return reservation;
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
                reservation.setId(resultSet.getInt("reservationId"));
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
