package use_case.change_password;

public interface ChangePasswordInputBoundary {
    /**
     * Execute the Change Password Use Case.
     */
    void execute(ChangePasswordInputData inputData);
}
