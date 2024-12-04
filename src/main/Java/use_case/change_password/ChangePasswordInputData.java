package use_case.change_password;

/**
 * The intput data for the Change Password Use Case.
 */

public class ChangePasswordInputData {
    private final String username;
    private final String currentPassword;
    private final String newPassword;
    private final String confirmPassword;

    public ChangePasswordInputData(String username, String currentPassword, String newPassword, String confirmPassword) {
        this.username = username;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    public String getUsername() {return username;}

    public String getCurrentPassword() {
        return currentPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

}
