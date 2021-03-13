package men.suruceanu.exchange.dto.exception;


public class ExchangeNotFoundException extends Exception{

    public ExchangeNotFoundException() {
    }

    public ExchangeNotFoundException(String message) {
        super(message);
    }

    public ExchangeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExchangeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
