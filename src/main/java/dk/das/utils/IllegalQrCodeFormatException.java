package dk.das.utils;

public class IllegalQrCodeFormatException extends RuntimeException {
    public IllegalQrCodeFormatException() {
    }

    public IllegalQrCodeFormatException(String message) {
        super(message);
    }

    public IllegalQrCodeFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalQrCodeFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalQrCodeFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
