package ba.unsa.etf.rpr;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

import ba.unsa.etf.rpr.dao.RoomDaoSQLImplementation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class App {
    private static final Option getUsers = new Option("getUsers", "get-users",false, "Printing all users from database");
    private static final Option getRooms = new Option("getRooms", "get-rooms",false, "Printing all rooms from database");
    private static final Option getReservations = new Option("getResvs", "get-reservations",false, "Printing all reservations from database");
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(getReservations);
        options.addOption(getRooms);
        options.addOption(getUsers);
        return options;
    }
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-[option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static void main(String[] args) {

    }
}
