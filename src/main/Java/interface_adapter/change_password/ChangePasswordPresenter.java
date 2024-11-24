package interface_adapter.change_password;

import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;


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

        // Optionally show success message on UI (this would typically be done in a UI component)
        System.out.println(outputData.getMessage());
    }

    @Override
    public void presentFailure(String errorMessage) {
        ChangePasswordState state = viewModel.getState();
        state.setConfirmPasswordError(errorMessage);  // Update error message in ViewModel
        viewModel.setState(state);

        // Optionally show error message on UI
        System.err.println(errorMessage);
    }
}
