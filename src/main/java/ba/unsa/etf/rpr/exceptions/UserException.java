package ba.unsa.etf.rpr.exceptions;

import ba.unsa.etf.rpr.domain.User;

public class UserException extends Exception{
    public UserException(String message, Exception exception) {
        super(message,exception);
    }
    public UserException(String message) {
        super(message);
    }
}
