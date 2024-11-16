package use_case.signup.general_user_signup;

public interface UserSignupOutputBoundary {
    /**
     * Prepares the success view for the Signup Use Case.
     * @param userSignupOutputData the output data
     */
    void prepareSuccessView(UserSignupOutputData userSignupOutputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
