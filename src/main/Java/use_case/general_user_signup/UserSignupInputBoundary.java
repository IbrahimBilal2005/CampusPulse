package use_case.general_user_signup;

public interface UserSignupInputBoundary {

    /**
     * Executes the event poster signup use case.
     * @param userSignupInputData the input data
     */
    void execute(UserSignupInputData userSignupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}