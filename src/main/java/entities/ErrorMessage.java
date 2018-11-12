package entities;

public class ErrorMessage {

    private boolean isError;

    private String message;

    public ErrorMessage(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }
}
