package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.GuestDaoSQLImplementation;
import ba.unsa.etf.rpr.dao.ReservationDaoSQLImplementation;
import ba.unsa.etf.rpr.dao.RoomDaoSQLImplementation;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Connection connection;
        try {
            final Properties login = new Properties();
            login.load(new FileInputStream("src/main/java/ba/unsa/etf/rpr/login.properties"));
            connection = DriverManager.getConnection(login.getProperty("connection.string"), login.getProperty("username"), login.getProperty("password"));
            PreparedStatement stmt1 = connection.prepareStatement("INSERT INTO Rooms (maxPersons) Values (2)");
            PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO Rooms (maxPersons) Values (2)");
            PreparedStatement stmt3 = connection.prepareStatement("INSERT INTO Rooms (maxPersons) Values (3)");
            PreparedStatement stmt4 = connection.prepareStatement("INSERT INTO Guests (firstName, lastName, city,country,email,phone) " +
                                                                      "Values ('Herbert','Osborn','Sarajevo','Bosna i Hercegovina','hosborn@gmail.com','061061041')");
            PreparedStatement stmt5 = connection.prepareStatement("INSERT INTO Guests (firstName, lastName, city,country,email,phone) " +
                    "Values ('Zack','Benson','Banja Luka','Bosna i Hercegovina','zbenson@gmail.com','061123456')");
            PreparedStatement stmt6 = connection.prepareStatement("INSERT INTO Reservations (arrivalDate,leaveDate,paymentAmount,additionalInfo,guestId,roomId) " +
                    "Values (str_to_date('12/12/2002','%d/%m/%Y'),str_to_date('04/01/2003','%d/%m/%Y'),1000.12,'',1,2)");
            PreparedStatement stmt7 = connection.prepareStatement("INSERT INTO Reservations (arrivalDate,leaveDate,paymentAmount,additionalInfo,guestId,roomId) " +
                    "Values (str_to_date('13/12/2002','%d/%m/%Y'),str_to_date('02/01/2003','%d/%m/%Y'),999.99,'',2,3)");
            Statement stmt12 = connection.createStatement();
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt4.executeUpdate();
            stmt5.executeUpdate();
            stmt6.executeUpdate();
            stmt7.executeUpdate();
            PreparedStatement stmt9= connection.prepareStatement("DELETE FROM  Guests");
            PreparedStatement stmt10 = connection.prepareStatement("DELETE FROM Rooms");
            PreparedStatement stmt8 = connection.prepareStatement("DELETE FROM  Reservations");
            ReservationDaoSQLImplementation resDao = new ReservationDaoSQLImplementation();

            java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2002-01-01");
            java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2004-01-01");
            for(Reservation res : resDao.searchByDateRange(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()))) {
                System.out.println(res);
            }
            stmt8.executeUpdate();
            stmt9.executeUpdate();
            stmt10.executeUpdate();
            stmt12.executeUpdate("ALTER TABLE Reservations AUTO_INCREMENT = 1;");
            stmt12.executeUpdate("ALTER TABLE Guests AUTO_INCREMENT = 1;");
            stmt12.executeUpdate("ALTER TABLE Rooms AUTO_INCREMENT = 1;");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
