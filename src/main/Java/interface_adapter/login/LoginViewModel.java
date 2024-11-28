package interface_adapter.login;

import interface_adapter.ViewModel;

public class LoginViewModel extends ViewModel<LoginState> {

    public final String LOGIN_SCREEN = "Login Screen";
    public final String USERNAME = "Username: ";
    public final String PASSWORD = "Password: ";
    public final String LOG_IN = "Login";
    public final String TITLE = "CampusPulse";

    public LoginViewModel() {
        super("Login_screen");
        setState(new LoginState());
    }
}
