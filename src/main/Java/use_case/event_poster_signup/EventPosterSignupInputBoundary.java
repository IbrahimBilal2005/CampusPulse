package use_case.event_poster_signup;

public interface EventPosterSignupInputBoundary {

    /**
     * Executes the event poster signup use case.
     * @param eventPosterSignupInputData the input data
     */
    void execute(EventPosterSignupInputData eventPosterSignupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
