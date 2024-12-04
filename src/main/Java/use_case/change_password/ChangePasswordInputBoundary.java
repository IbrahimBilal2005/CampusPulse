package use_case.change_password;

/**
 * The input boundary for the Change Password Use Case.
 */

public interface ChangePasswordInputBoundary {
    /**
     * Execute the Change Password Use Case.
     */
    void execute(ChangePasswordInputData inputData);
}
