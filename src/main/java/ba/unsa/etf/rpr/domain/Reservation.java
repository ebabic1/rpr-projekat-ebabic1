package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * Holds every room reservation
 * @author  Eldar Babić
 */
public class Reservation implements IDable{
    private int id;
    private Date arrivalDate;
    private Date leaveDate;
    private String additionalInfo;
    private User user;
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return getId() == that.getId() && Objects.equals(getArrivalDate(), that.getArrivalDate()) && Objects.equals(getLeaveDate(), that.getLeaveDate()) && Objects.equals(getAdditionalInfo(), that.getAdditionalInfo()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getRoom(), that.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArrivalDate(), getLeaveDate(), getAdditionalInfo(), getUser(), getRoom());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", arrivalDate=" + arrivalDate +
                ", leaveDate=" + leaveDate +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", user=" + user +
                ", room=" + room +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int reservationId) {
        this.id = reservationId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


}
