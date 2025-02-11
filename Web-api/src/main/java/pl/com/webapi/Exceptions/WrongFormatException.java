package pl.com.webapi.Exceptions;

public class WrongFormatException extends RuntimeException{
    public WrongFormatException(){
        super("Wrong Format!");
    }
}
