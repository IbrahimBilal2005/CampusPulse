package entity;
import entity.Interfaces.UserInterface;

/**
 * Base user in our program
 */
public class CommonUser implements UserInterface {

    private final String username;
    private String password;

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getName() { return this.username; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void changePassword(String newPassword) { this.password = newPassword; }
}
