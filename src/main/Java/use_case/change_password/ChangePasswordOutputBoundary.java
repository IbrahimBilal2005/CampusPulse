package use_case.change_password;

public interface ChangePasswordOutputBoundary {
    void presentSuccess(ChangePasswordOutputData outputData);
    void presentFailure(String errorMessage);
}
