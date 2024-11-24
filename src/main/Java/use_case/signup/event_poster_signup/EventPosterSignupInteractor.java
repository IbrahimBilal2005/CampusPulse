package use_case.signup.event_poster_signup;

import entity.Account;
import entity.AccountCreationStrategy;

public class EventPosterSignupInteractor implements EventPosterSignupInputBoundary{

    private final EventPosterUserDataAccessInterface userDataAccessObject;
    private final EventPosterSignupOutputBoundary userPresenter;
    private final AccountCreationStrategy accountCreator;

    public EventPosterSignupInteractor(EventPosterUserDataAccessInterface userDataAccessObject,
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