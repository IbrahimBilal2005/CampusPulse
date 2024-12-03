package use_case.signup.event_poster_signup;

public interface EventPosterSignupInputBoundary {

    /**
     * Executes the event poster signup use case.
     * @param eventPosterSignupInputData the input data
     */
    void execute(EventPosterSignupInputData eventPosterSignupInputData);
}
