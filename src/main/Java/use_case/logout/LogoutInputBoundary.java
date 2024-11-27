package use_case.logout;

/**
 * Input Boundary for actions related to logging out
 */
public interface LogoutInputBoundary {

    /**
     * Executes the logout use case.
     * @param logoutInputData the input data
     */
    void execute(LogoutInputData logoutInputData);
}
