package use_case.change_password;

import entity.UserInterface;
import entity.UserInterface;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordCommonUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(UserInterface user);
}
