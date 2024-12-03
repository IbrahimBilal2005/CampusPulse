package use_case.login;

/**
 * Input Boundary for actions which are related to logging in
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case
     */
    void execute(LoginInputData loginInputData);
}
