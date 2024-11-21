package use_case.change_password;

public class ChangePasswordInputData {
    private final String username;
    private final String currentPassword;
    private final String newPassword;

    public ChangePasswordInputData(String username, String currentPassword, String newPassword) {
        this.username = username;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

}
