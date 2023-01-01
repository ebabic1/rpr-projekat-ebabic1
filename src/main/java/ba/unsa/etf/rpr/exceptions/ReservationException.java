package ba.unsa.etf.rpr.exceptions;

public class ReservationException extends Exception{
    public ReservationException(String message, Exception exception) {
        super(message,exception);
    }
    public ReservationException(String message) {
        super(message);
    }

}
