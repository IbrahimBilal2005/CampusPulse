package use_case.logout;

/**
 * The input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}
