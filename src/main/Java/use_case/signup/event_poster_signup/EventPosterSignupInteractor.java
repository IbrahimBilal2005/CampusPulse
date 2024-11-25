package use_case.signup.event_poster_signup;

import entity.Account;
import entity.AccountCreationStrategy;
import use_case.signup.UserSignupDataAccessInterface;

public class EventPosterSignupInteractor implements EventPosterSignupInputBoundary{

    private final UserSignupDataAccessInterface userDataAccessObject;
    private final EventPosterSignupOutputBoundary userPresenter;
    private final AccountCreationStrategy accountCreator;

    public EventPosterSignupInteractor(UserSignupDataAccessInterface userDataAccessObject,
                                       EventPosterSignupOutputBoundary eventPosterSignupOutputBoundary,
                                       AccountCreationStrategy accountCreator) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = eventPosterSignupOutputBoundary;
        this.accountCreator = accountCreator;
    }

    @Override
    public void execute(EventPosterSignupInputData eventPosterSignupInputData){
        if (userDataAccessObject.existsByName(eventPosterSignupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!eventPosterSignupInputData.getPassword().equals(eventPosterSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords do not match.");
        }
        else {
            final Account eventPoster = accountCreator.createAccount(
                    eventPosterSignupInputData.getUsername(),
                    eventPosterSignupInputData.getPassword(),
                    eventPosterSignupInputData.getRepeatPassword(),
                    eventPosterSignupInputData.getOrganizationName(),
                    eventPosterSignupInputData.getSopLink());

            userDataAccessObject.save(eventPoster);
            final EventPosterSignupOutputData signupOutputData = new EventPosterSignupOutputData(eventPoster.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToBaseView() { userPresenter.switchToBaseView(); }
}