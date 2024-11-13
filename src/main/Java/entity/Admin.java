package entity;

/**
 * An admin in our program.
 */
public class Admin implements Account {
    private final String username;
    private final String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return this.password;
    }
}
