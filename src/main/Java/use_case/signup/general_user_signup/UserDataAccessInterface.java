package use_case.signup.general_user_signup;

import entity.Account;

public interface UserDataAccessInterface {
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
