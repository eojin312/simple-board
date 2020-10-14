package hachi.simpleboard.exception;

public class FailPlusReadCountException extends RuntimeException {
    public FailPlusReadCountException() {
        super();
    }

    public FailPlusReadCountException(String message) {
        super(message);
    }

    public FailPlusReadCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailPlusReadCountException(Throwable cause) {
        super(cause);
    }
}
