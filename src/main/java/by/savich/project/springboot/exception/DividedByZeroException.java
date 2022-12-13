package by.savich.project.springboot.exception;

public class DividedByZeroException extends RuntimeException {
    public DividedByZeroException(String message) {
        super(message);
    }
}
