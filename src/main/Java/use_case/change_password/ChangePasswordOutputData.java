package use_case.change_password;

/**
 * The output data for the Change Password Use Case.
 */

public class ChangePasswordOutputData {
    private final String message;

    public ChangePasswordOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
