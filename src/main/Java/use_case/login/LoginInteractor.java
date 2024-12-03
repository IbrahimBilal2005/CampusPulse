package use_case.login;

/**
 * Login Interactor
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

        // Check if the username is in the database or not, if it is then continue on
        if (!userDataAccessObject.nameExists(username)) {
            loginPresenter.prepareFailView("Account " + username + " does not exist.");
            return;
        }

        // Check if the password is correct for the username, if it is then continue on
        String storedPassword = userDataAccessObject.get(username).getPassword();
        if (!password.equals(storedPassword)) {
            loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            return;
        }

        // The user has been authenticated, create an output data and go to the presenter
        LoginOutputData outputData = new LoginOutputData(false);
        loginPresenter.prepareSuccessView(outputData);
    }
}
