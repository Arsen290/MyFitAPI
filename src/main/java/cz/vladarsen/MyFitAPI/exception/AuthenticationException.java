package cz.vladarsen.MyFitAPI.exception;

public class AuthenticationException extends RuntimeException {
    private final String errorCode;
    public AuthenticationException(String message, String errorCode) {
        super("Authentication failed: " + message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
