package entity;

/**
 * Represents an admin account in the program.
 * <p>
 * Admins have the ability to approve or deny events and manage event posters.
 * This class provides basic functionality to represent an admin's credentials
 * and placeholder comments for potential additional logic related to the approval process.
 * </p>
 */
public class Admin implements Account {
    private final String username;
    private  String password;

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

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUsername(String username) {

    }
}
