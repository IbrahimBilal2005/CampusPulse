package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputData;
import use_case.change_password.ChangePasswordInputBoundary;

public class ChangePasswordController {
    private final ChangePasswordInputBoundary interactor;

    public ChangePasswordController(ChangePasswordInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the change password request.
     * @param username the username of the user
     * @param currentPassword the current password
     * @param newPassword the new password
     * @param confirmPassword the confirm password
     */
    public void execute(String username, String currentPassword, String newPassword, String confirmPassword) {
        // Create ChangePasswordInputData with collected input
        ChangePasswordInputData inputData = new ChangePasswordInputData(username, currentPassword, newPassword, confirmPassword);
        interactor.execute(inputData);  // Call the interactor to handle the password change
    }

}
