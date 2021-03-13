package men.suruceanu.exchange.exception;

public class ExchangeHistoryNotFoundException extends NotFoundException {

    public ExchangeHistoryNotFoundException() {
    }

    public ExchangeHistoryNotFoundException(String message) {
        super(message);
    }

    public ExchangeHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeHistoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExchangeHistoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
