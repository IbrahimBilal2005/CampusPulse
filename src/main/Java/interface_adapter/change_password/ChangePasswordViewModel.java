package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The view model for the Change Password Use Case.
 */

public class ChangePasswordViewModel extends ViewModel <ChangePasswordState> {
    public static final String TITLE_LABEL = "Change Password";
    public static final String USERNAME_LABEL = "Username";
    public static final String CURRENT_PASSWORD_LABEL = "Current Password";
    public static final String NEW_PASSWORD_LABEL = "New Password";
    public static final String CONFIRM_PASSWORD_LABEL = "Confirm Password";

    public ChangePasswordViewModel() {
        super("Change Password Screen");
        setState(new ChangePasswordState());
    }
}
