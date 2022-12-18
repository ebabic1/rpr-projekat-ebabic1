package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * Holds every room reservation
 * @author  Eldar Babić
 */
public class Reservation {
    private int reservationId;
    private Date arrivalDate;
    private Date leaveDate;
    private String additionalInfo;
    private int guestId;
    private int roomId;

    @Override
    public String toString() {
        return "Booking{" +
                "reservationId=" + reservationId +
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
        return getReservationId() == booking.getReservationId()  && getGuestId() == booking.getGuestId() && getRoomId() == booking.getRoomId() && getArrivalDate().equals(booking.getArrivalDate()) && getLeaveDate().equals(booking.getLeaveDate()) && getAdditionalInfo().equals(booking.getAdditionalInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReservationId(), getArrivalDate(), getLeaveDate(), getAdditionalInfo(), getGuestId(), getRoomId());
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
