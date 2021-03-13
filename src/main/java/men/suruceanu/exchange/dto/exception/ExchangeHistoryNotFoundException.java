package men.suruceanu.exchange.dto.exception;


public class ExchangeHistoryNotFoundException extends Exception{

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
