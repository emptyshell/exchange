package men.suruceanu.exchange.exception;

public class BranchNotFoundException extends NotFoundException {

    public BranchNotFoundException() {
    }

    public BranchNotFoundException(String message) {
        super(message);
    }

    public BranchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BranchNotFoundException(Throwable cause) {
        super(cause);
    }

    public BranchNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
