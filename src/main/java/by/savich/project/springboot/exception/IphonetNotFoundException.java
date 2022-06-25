package by.savich.project.springboot.exception;

public class IphonetNotFoundException extends RuntimeException{
    public IphonetNotFoundException(String message) {
        super(message);
    }
}
