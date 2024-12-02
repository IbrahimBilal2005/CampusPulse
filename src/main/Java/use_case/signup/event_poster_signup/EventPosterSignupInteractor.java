package use_case.signup.event_poster_signup;

import entity.Account;
import entity.AccountCreationStrategy;
import entity.EventPoster;
import use_case.signup.AccountSignupDataAccessInterface;

public class EventPosterSignupInteractor implements EventPosterSignupInputBoundary{

    private final AccountSignupDataAccessInterface accountSignupDataAccessInterface;
    private final EventPosterSignupOutputBoundary userPresenter;
    private final AccountCreationStrategy accountCreator;

    public EventPosterSignupInteractor(AccountSignupDataAccessInterface accountSignupDataAccessInterface,
                                       EventPosterSignupOutputBoundary eventPosterSignupOutputBoundary,
                                       AccountCreationStrategy accountCreator) {
        this.accountSignupDataAccessInterface = accountSignupDataAccessInterface;
        this.userPresenter = eventPosterSignupOutputBoundary;
        this.accountCreator = accountCreator;
    }

    @Override
    public void execute(EventPosterSignupInputData eventPosterSignupInputData){
        if (accountSignupDataAccessInterface.existsByName(eventPosterSignupInputData.getUsername())) {
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

            accountSignupDataAccessInterface.save((EventPoster) eventPoster);
            final EventPosterSignupOutputData signupOutputData = new EventPosterSignupOutputData(eventPoster.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToBaseView() { userPresenter.switchToBaseView(); }
}