package use_case.change_password;

import entity.Account;

public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccess;
    private final ChangePasswordOutputBoundary presenter;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface userDataAccess,
                                    ChangePasswordOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }
    @Override
    public void execute(ChangePasswordInputData inputData) {
        // Validate that the new password matches the confirmation password
        if (!inputData.getNewPassword().equals(inputData.getConfirmPassword())) {
            presenter.presentFailure("New password and confirmation do not match.");
            return;
        }

        // Retrieve the account by username
        Account account = userDataAccess.getAccountByUsername(inputData.getUsername());
        if (account == null) {
            presenter.presentFailure("Account not found.");
            return;
        }

        // Validate the current password
        if (!account.getPassword().equals(inputData.getCurrentPassword())) {
            presenter.presentFailure("Incorrect current password.");
            return;
        }

        // Update the password
        userDataAccess.changePassword(account);

        // Notify success
        presenter.presentSuccess(new ChangePasswordOutputData("Password successfully changed."));
    }
}
