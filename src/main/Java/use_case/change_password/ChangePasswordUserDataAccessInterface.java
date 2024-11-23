package use_case.change_password;

import entity.Account;

/**
 * The interface of the DAO for the Change Password Use Case.
 */

public interface ChangePasswordUserDataAccessInterface {
    /**
     * Updates the system to record this user's password.
     */
    Account getAccountByUsername(String username);

    void changePassword(Account user);
}

