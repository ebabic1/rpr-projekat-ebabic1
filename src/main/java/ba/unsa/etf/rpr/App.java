package ba.unsa.etf.rpr;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main( String[] args )
    {
        launch();
        /*Connection connection;
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
        }*/
    }
}
