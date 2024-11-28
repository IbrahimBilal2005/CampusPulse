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


    void setUsername(String username);

    void setPassword(String password);
}
