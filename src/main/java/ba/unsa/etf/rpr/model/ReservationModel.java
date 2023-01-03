package ba.unsa.etf.rpr.model;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.domain.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class ReservationModel {


    public SimpleObjectProperty<LocalDate> arrivalDate = new SimpleObjectProperty();
    public SimpleObjectProperty<LocalDate> leaveDate = new SimpleObjectProperty();
    public SimpleStringProperty additionalInfo = new SimpleStringProperty();
    public SimpleObjectProperty<Room> room = new SimpleObjectProperty<>();
    public SimpleObjectProperty<User> user = new SimpleObjectProperty<>();
    public Room getRoom() {
        return room.get();
    }

    public SimpleObjectProperty<Room> roomProperty() {
        return room;
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    public User getUser() {
        return user.get();
    }

    public SimpleObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }
    public Object getArrivalDate() {
        return arrivalDate.get();
    }

    public SimpleObjectProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public Object getLeaveDate() {
        return leaveDate.get();
    }

    public SimpleObjectProperty leaveDateProperty() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate.set(leaveDate);
    }

    public String getAdditionalInfo() {
        return additionalInfo.get();
    }

    public SimpleStringProperty additionalInfoProperty() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo.set(additionalInfo);
    }
    public void fromReservation(Reservation r) {
        this.additionalInfo.set(r.getAdditionalInfo());
        this.arrivalDate.set(r.getArrivalDate().toLocalDate());
        this.leaveDate.set(r.getLeaveDate().toLocalDate());
        this.room.set(r.getRoom());
        this.user.set(r.getUser());
    }
    public Reservation toReservation(){
        Reservation r = new Reservation();
        System.out.println(this.room.getValue());
        r.setRoom(this.room.getValue());
        r.setUser(this.user.getValue());
        r.setAdditionalInfo(this.additionalInfo.getValue());
        r.setArrivalDate(Date.valueOf(this.arrivalDate.getValue()));
        r.setLeaveDate(Date.valueOf(this.leaveDate.getValue()));
        return r;
    }
}
