package use_case.signup;

import entity.Account;

/**
 * Data access interface for signup use case.
 */
public interface AccountSignupDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(Account user);
}
