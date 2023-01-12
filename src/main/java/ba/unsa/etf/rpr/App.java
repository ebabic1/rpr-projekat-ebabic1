package ba.unsa.etf.rpr;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ReservationException;
import ba.unsa.etf.rpr.exceptions.RoomException;
import ba.unsa.etf.rpr.exceptions.UserException;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class App {
    private static final Option getUsers = new Option("getUs", "get-users",false, "Printing all users from database");
    private static final Option getRooms = new Option("getRm", "get-rooms",false, "Printing all rooms from database");
    private static final Option getReservations = new Option("getRe", "get-reservations",false, "Printing all reservations from database");
    private static final Option addUser = new Option("us","add-user",false,"Adding a user to the database");
    private static final Option addRoom = new Option("rm","add-room",false,"Adding a room to the database");
    private static final Option addReservation = new Option("rs","add-reservation",false,"Adding a reservation to the database");

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(getReservations);
        options.addOption(getRooms);
        options.addOption(getUsers);
        options.addOption(addUser);
        options.addOption(addReservation);
        options.addOption(addRoom);
        return options;
    }
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-[option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static void main(String[] args) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);
        if(commandLine.hasOption(getReservations.getOpt()) || commandLine.hasOption(getReservations.getLongOpt())){
            ReservationManager reservationManager = new ReservationManager();
            reservationManager.getAll().forEach(System.out::println);
        }
        else if(commandLine.hasOption(getRooms.getOpt()) || commandLine.hasOption(getRooms.getLongOpt())){
            RoomManager roomManager = new RoomManager();
            roomManager.getAll().forEach(System.out::println);
        }
        else if(commandLine.hasOption(getUsers.getOpt()) || commandLine.hasOption(getUsers.getLongOpt())){
            UserManager userManager = new UserManager();
            userManager.getAll().forEach(System.out::println);
        }
        else if(commandLine.hasOption(addRoom.getOpt()) || commandLine.hasOption(addRoom.getLongOpt())){
            Scanner s = new Scanner(System.in);
            Room r = new Room();
            System.out.print("Room number: ");
            int roomNumber = s.nextInt();
            r.setRoomNumber(roomNumber);
            System.out.print("Max persons: ");
            int maxPersons = s.nextInt();
            r.setMaxPersons(maxPersons);
            System.out.print("Price: ");
            double price = s.nextDouble();
            r.setPrice(price);
            System.out.println("Description: ");
            String description = s.nextLine();
            r.setDescription(description);
            RoomManager roomManager = new RoomManager();
            try {
                roomManager.add(r);
            } catch (RoomException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(commandLine.hasOption(addUser.getOpt()) || commandLine.hasOption(addUser.getLongOpt())){
            Scanner s = new Scanner(System.in);
            User u = new User();
            System.out.print("First name: ");
            u.setFirstName(s.nextLine());
            System.out.print("Last name: ");
            u.setLastName(s.nextLine());
            System.out.print("City: ");
            u.setCity(s.nextLine());
            System.out.print("Country: ");
            u.setCountry(s.nextLine());
            System.out.print("Email: ");
            u.setEmail(s.nextLine());
            System.out.print("Phone: ");
            u.setPhone(s.nextLine());
            System.out.print("Password: ");
            u.setPassword(s.nextLine());
            System.out.println("Username: ");
            u.setUsername(s.nextLine());
            u.setAdmin(0);
            UserManager userManager = new UserManager();
            try {
                userManager.add(u);
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(commandLine.hasOption(addReservation.getOpt()) || commandLine.hasOption(addReservation.getLongOpt())){
            Scanner s = new Scanner(System.in);
            Reservation r = new Reservation();
            System.out.print("Arrival date (dd-mm-yyyy)");
            String[] n =  s.nextLine().split("-");
            r.setArrivalDate(Date.valueOf(LocalDate.of(Integer.parseInt(n[2]),Integer.parseInt(n[1]),Integer.parseInt(n[0]))));
            System.out.print("Leave date (dd-mm-yyyy)");
            n =  s.nextLine().split("-");
            r.setLeaveDate(Date.valueOf(LocalDate.of(Integer.parseInt(n[2]),Integer.parseInt(n[1]),Integer.parseInt(n[0]))));
            System.out.print("User ID: ");
            r.setUser(DaoFactory.userDao().getById(s.nextInt()));
            System.out.print("Room ID: ");
            r.setRoom(DaoFactory.roomDao().getById(s.nextInt()));
            System.out.println("Additional info: ");
            r.setAdditionalInfo(s.nextLine());
            ReservationManager reservationManager = new ReservationManager();
            try {
                reservationManager.add(r);
            } catch (ReservationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
