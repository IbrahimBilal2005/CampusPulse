package use_case.signup.general_user_signup;

// import entity.Account;
// import entity.UserFactory

public class UserSignupInteractor implements UserSignupInputBoundary {

    private final UserDataAccessInterface userDataAccessInterface;
    private final UserSignupOutputBoundary userPresenter;
    // private final Userfactory userFactory;

    public UserSignupInteractor(UserDataAccessInterface userDataAccessInterface,
                                UserSignupOutputBoundary userSignupOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = userSignupOutputBoundary;
    }


    @Override
    public void execute(UserSignupInputData userSignupInputData) {
        // Logic for creating the event poster user. Dependent on database and user factories.
        // will work on implementing this next.
    }

    @Override
    public void switchToLoginView() {
        // TODO userPresenter.switchToLoginView();

    }
}