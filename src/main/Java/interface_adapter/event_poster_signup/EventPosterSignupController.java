package interface_adapter.event_poster_signup;

import use_case.signup.event_poster_signup.EventPosterSignupInputBoundary;
import use_case.signup.event_poster_signup.EventPosterSignupInputData;

public class EventPosterSignupController {

    private final EventPosterSignupInputBoundary eventPosterSignupUseCaseInteractor;

    public EventPosterSignupController(EventPosterSignupInputBoundary eventPosterSignupUseCaseInteractor1) {
        this.eventPosterSignupUseCaseInteractor = eventPosterSignupUseCaseInteractor1;
    }

    /**
     * Executes the EventPoster Signup Use Case.
     * @param username username to sign up
     * @param password the password
     * @param confirmPassword the confirmed password
     * @param organizationName the name of the event poster's organization
     * @param sopLink the official sop link for the event poster's organization
     */
    public void execute(String username,
                        String password,
                        String confirmPassword,
                        String organizationName,
                        String sopLink) {
        final EventPosterSignupInputData eventPosterSignupInputData = new EventPosterSignupInputData(
                username, password, confirmPassword, organizationName, sopLink);

        eventPosterSignupUseCaseInteractor.execute(eventPosterSignupInputData);

    }
}
