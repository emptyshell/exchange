package men.suruceanu.exchange.dto.exception;


public class RegisterEmployeeException extends Exception{

    public RegisterEmployeeException() {
    }

    public RegisterEmployeeException(String message) {
        super(message);
    }

    public RegisterEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterEmployeeException(Throwable cause) {
        super(cause);
    }

    public RegisterEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
