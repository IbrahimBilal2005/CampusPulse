package use_case.login;

/**
 * The output boundary for the login use case
 */
public interface LoginOutputBoundary {
    /**
     * Changes the view to the home screen after properly authenticating the user
     * @param outputData
     */
    void prepareSuccessView(LoginOutputData outputData);

    /**
     * Does not change the view and informs the user of what error they made while inputting information
     * @param errorMessage
     */
    void prepareFailView(String errorMessage);
}
