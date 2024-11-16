package use_case.signup.event_poster_signup;

// import entity.Account;
// import entity.UserFactory

import use_case.signup.general_user_signup.UserDataAccessInterface;

public class EventPosterSignupInteractor implements EventPosterSignupInputBoundary{

    private final EventPosterUserDataAccessInterface userDataAccessInterface;
    private final EventPosterSignupOutputBoundary userPresenter;
    // private final Userfactory userFactory;

    public EventPosterSignupInteractor(EventPosterUserDataAccessInterface userDataAccessInterface,
                                       EventPosterSignupOutputBoundary eventPosterSignupOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = eventPosterSignupOutputBoundary;
    }

    @Override
    public void execute(EventPosterSignupInputData eventPosterSignupInputData){
        // Logic for creating the event poster user. Dependent on database and user factories.
        // will work on implementing this next.
    }

    @Override
    public void switchToLoginView() {
        // TODO userPresenter.switchToLoginView();

    }
}