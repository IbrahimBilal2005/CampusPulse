package interface_adapter.login;

import interface_adapter.ViewModel;

public class LoginViewModel extends ViewModel<LoginState> {

    public static final String LOGIN_SCREEN = "Login Screen";
    public static final String USERNAME = "Username: ";
    public static final String PASSWORD = "Password: ";
    public static final String LOG_IN = "Login";
    public static final String TITLE = "CampusPulse";

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }
}
