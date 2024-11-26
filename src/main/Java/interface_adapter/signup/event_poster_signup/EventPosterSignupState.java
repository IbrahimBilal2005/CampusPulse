package interface_adapter.signup.event_poster_signup;

import entity.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * The state for the Signup View Model.
 */
public class EventPosterSignupState {
    private String username = "";
    private String usernameError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;
    private String organizationName;
    private String sopLink;
    private Map<String, Event> events = new HashMap<String, Event>();

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public String getOrganizationName() { return organizationName; }

    public String getSopLink() { return sopLink; }

    public Map<String, Event> getEvents() { return events; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public void setSopLink(String sopLink) { this.sopLink = sopLink; }

    @Override
    public String toString() {
        return "EventPosterSignupState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + ", organizationName='" + organizationName + '\''
                + ", sopLink='" + sopLink + '\''
                + ", events='" + events + '\''
                + '}';
    }
}
