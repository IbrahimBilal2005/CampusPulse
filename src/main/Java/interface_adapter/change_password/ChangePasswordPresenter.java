package interface_adapter.change_password;

import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The presenter for the Change Password Use Case.
 */


public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {
    private final ChangePasswordViewModel viewModel;

    public ChangePasswordPresenter(ChangePasswordViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSuccess(ChangePasswordOutputData outputData) {
        ChangePasswordState state = viewModel.getState();
        state.setCurrentPasswordError(null);
        state.setNewPasswordError(null);
        state.setConfirmPasswordError(null);
        viewModel.setState(state);  // Clear all errors and update success message

    }

    @Override
    public void presentFailure(String errorMessage) {
        ChangePasswordState state = viewModel.getState();
        state.setConfirmPasswordError(errorMessage);  // Update error message in ViewModel
        viewModel.setState(state);

    }
    public ChangePasswordViewModel getViewModel() {
        return viewModel;
    }
}
