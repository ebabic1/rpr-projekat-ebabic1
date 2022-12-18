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
    private int guestId;
    private int roomId;

    @Override
    public String toString() {
        return "Booking{" +
                "reservationId=" + id +
                ", arrivalDate=" + arrivalDate +
                ", leaveDate=" + leaveDate +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", guestId=" + guestId +
                ", roomId=" + roomId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation booking = (Reservation) o;
        return getId() == booking.getId()  && getGuestId() == booking.getGuestId() && getRoomId() == booking.getRoomId() && getArrivalDate().equals(booking.getArrivalDate()) && getLeaveDate().equals(booking.getLeaveDate()) && getAdditionalInfo().equals(booking.getAdditionalInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArrivalDate(), getLeaveDate(), getAdditionalInfo(), getGuestId(), getRoomId());
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
