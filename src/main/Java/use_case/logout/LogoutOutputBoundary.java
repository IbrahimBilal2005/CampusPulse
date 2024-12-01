package use_case.logout;

/**
 * The output boundary for the Logout Use Case.
 */
public interface LogoutOutputBoundary {
    /**
     * Prepares the success view for the Logout Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LogoutOutputData outputData);

    /**
     * Prepares the failure view for the Logout Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}