package use_case.logout;

/**
 * Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessObject,
                            LogoutOutputBoundary logoutPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.logoutPresenter = logoutPresenter;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUsername();
        userDataAccessObject.setCurentusername(null);
        final LogoutOutputData logoutOutputData = new LogoutOutputData(username, false);
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
