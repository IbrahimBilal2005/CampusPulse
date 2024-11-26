package entity;

/**
 * An admin in our program.
 */
public class Admin implements Account {
    private final String username;
    private final String password;

    // TODO do we need to add any other logic to make our approval process easier?
    //  Ex map of approved event posters, map of pending event posters awaiting approval etc.
    // private final Map<Integer, EventPoster> approvedEventPosters
    // private final Map<Integer, EventPoster> pendingApproval

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

    public Account withPassword(String newPassword) {
        return new Admin(this.username, newPassword);
    }
}
