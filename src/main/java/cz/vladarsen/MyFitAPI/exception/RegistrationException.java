package cz.vladarsen.MyFitAPI.exception;

public class RegistrationException extends RuntimeException {

    private final String errorCode;

    public RegistrationException(String message, String errorCode) {
        super("Registration failed: " + message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
