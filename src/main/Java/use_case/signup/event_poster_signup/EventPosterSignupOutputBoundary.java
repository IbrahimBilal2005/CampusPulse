package use_case.signup.event_poster_signup;

/**
 * Output Boundary for the event Poster Signup
 */
public interface EventPosterSignupOutputBoundary {
    /**
     * Prepares the success view for the Signup Use Case.
     *
     * @param eventPosterSignupOutputData the output data
     */
    void prepareSuccessView(EventPosterSignupOutputData eventPosterSignupOutputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
