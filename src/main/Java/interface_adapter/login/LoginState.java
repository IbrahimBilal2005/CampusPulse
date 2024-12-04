package interface_adapter.login;

/**
 * The state of the screen at time of button click
 * This will save the username and password info inputted by the user which can then be used to compare to the
 * database elsewhere.
 */
public class LoginState {
    private String username = "";
    private String password = "";
    private String loginError;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
