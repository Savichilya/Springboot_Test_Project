package by.savich.project.springboot.exception;

public class IphoneNotFoundException extends RuntimeException{
    public IphoneNotFoundException(String message) {
        super(message);
    }
}
