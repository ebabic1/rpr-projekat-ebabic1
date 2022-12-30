package ba.unsa.etf.rpr.exceptions;

public class RoomException extends Exception{
    public RoomException(String message, Exception exception) {
        super(message,exception);
    }
    public RoomException(String message) {
        super(message);
    }
}