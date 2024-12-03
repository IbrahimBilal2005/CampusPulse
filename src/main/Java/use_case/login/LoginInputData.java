package use_case.login;

/**
 * The input data for the login use case
 * It contains the text within the username and password field at the time of clicking the login button
 */
public class LoginInputData {

    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
