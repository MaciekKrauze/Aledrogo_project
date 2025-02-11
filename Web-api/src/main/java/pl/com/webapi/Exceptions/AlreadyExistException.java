package pl.com.webapi.Exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message + "Already Exist Exception");
    }
}
