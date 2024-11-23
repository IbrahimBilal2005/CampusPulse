package use_case.change_password;

public class ChangePasswordOutputData {
    private final String message;

    public ChangePasswordOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
