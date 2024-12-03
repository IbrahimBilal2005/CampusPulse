package use_case.signup.event_poster_signup;

import entity.Account;
import entity.AccountCreationStrategy;
import entity.EventPoster;
import use_case.signup.UserSignupDataAccessInterface;

public class EventPosterSignupInteractor implements EventPosterSignupInputBoundary{

    private final UserSignupDataAccessInterface userSignupDataAccessInterface;
    private final EventPosterSignupOutputBoundary userPresenter;
    private final AccountCreationStrategy accountCreator;

    public EventPosterSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                       EventPosterSignupOutputBoundary eventPosterSignupOutputBoundary,
                                       AccountCreationStrategy accountCreator) {
        this.userSignupDataAccessInterface = userSignupDataAccessInterface;
        this.userPresenter = eventPosterSignupOutputBoundary;
        this.accountCreator = accountCreator;
    }

    @Override
    public void execute(EventPosterSignupInputData eventPosterSignupInputData){
        if (userSignupDataAccessInterface.existsByName(eventPosterSignupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!eventPosterSignupInputData.getPassword().equals(eventPosterSignupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords do not match.");
        }
        else if (!eventPosterSignupInputData.getSopLink().startsWith("https://")) {
            userPresenter.prepareFailView("Invalid SOP link");
        }
        else if (eventPosterSignupInputData.getOrganizationName().isEmpty()) {
            userPresenter.prepareFailView("Invalid organization name");
        }
        else if (eventPosterSignupInputData.getPassword().length() < 8) {
            userPresenter.prepareFailView("Password must be at least 8 characters");
        }
        else {
            final Account eventPoster = accountCreator.createAccount(
                    eventPosterSignupInputData.getUsername(),
                    eventPosterSignupInputData.getPassword(),
                    eventPosterSignupInputData.getOrganizationName(),
                    eventPosterSignupInputData.getSopLink(),
                    eventPosterSignupInputData.getEvents());

            userSignupDataAccessInterface.save((EventPoster) eventPoster);
            final EventPosterSignupOutputData signupOutputData = new EventPosterSignupOutputData(eventPoster.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}