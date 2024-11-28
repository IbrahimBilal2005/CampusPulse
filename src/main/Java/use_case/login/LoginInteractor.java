package use_case.login;

/**
 * COMPLETED FILE
 */
public class LoginInteractor implements LoginInputBoundary{
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        if (!userDataAccessObject.nameExists(username)) {
            loginPresenter.prepareFailView("Account " + username + " does not exist.");
            return;
        }

        String storedPassword = userDataAccessObject.get(username).getPassword();
        if (!password.equals(storedPassword)) {
            loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            return;
        }

        LoginOutputData outputData = new LoginOutputData(false);
        loginPresenter.prepareSuccessView(outputData);
    }
}
