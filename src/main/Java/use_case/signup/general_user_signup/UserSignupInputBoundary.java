package use_case.signup.general_user_signup;

/**
 * Interface for the User Sign up Input Boundary.
 */
public interface UserSignupInputBoundary {

    /**
     * Executes the event poster signup use case.
     * @param userSignupInputData the input data
     */
    void execute(UserSignupInputData userSignupInputData);
}
