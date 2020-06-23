package hachi.simpleboard.exception;

public class BadParameterExcpetion extends RuntimeException {
    public BadParameterExcpetion() {
        super();
    }

    public BadParameterExcpetion(String message) {
        super(message);
    }

    public BadParameterExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public BadParameterExcpetion(Throwable cause) {
        super(cause);
    }
}
