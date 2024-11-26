package entity;

/**
 * Interface representing an account in our program.
 */
public interface Account {
    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getUsername();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns a new Account instance with the updated password.
     * @param newPassword the new password to set.
     * @return a new Account instance with the updated password.
     */
    Account withPassword(String newPassword);
}
