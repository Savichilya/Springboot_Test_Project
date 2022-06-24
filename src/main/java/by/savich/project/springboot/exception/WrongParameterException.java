package by.savich.project.springboot.exception;

public class WrongParameterException extends RuntimeException{

    public  WrongParameterException (String message){
        super(message);
    }
}
