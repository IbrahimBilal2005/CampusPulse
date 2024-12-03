package use_case.change_password;

/**
 * The output boundary for the Change Password Use Case.
 */

public interface ChangePasswordOutputBoundary {

    /**
     * The function showing success for the Change Password Use Case.
     */

    void presentSuccess(ChangePasswordOutputData outputData);

    /**
     * The function showing failure for the Change Password Use Case.
     */

    void presentFailure(String errorMessage);
}
