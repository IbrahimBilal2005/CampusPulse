package use_case.login;

/**
 * COMPLETED FILE
 */
public interface LoginOutputBoundary {

    void prepareSuccessView(LoginOutputData outputData);

    void prepareFailView(String errorMessage);
}
