package entity.Interfaces;

/**
 * The representation of a user in our program.
 */
public interface UserInterface {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Change the password of the user
     */
    void changePassword(String newPassword);
}
