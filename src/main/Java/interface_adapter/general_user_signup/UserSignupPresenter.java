package interface_adapter.general_user_signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.general_user_signup.UserSignupOutputBoundary;
import use_case.signup.general_user_signup.UserSignupOutputData;

public class UserSignupPresenter implements UserSignupOutputBoundary {
    private final UserSignupViewModel userSignupViewModel;
    private final ViewManagerModel viewManagerModel;
    // TODO private final LoginViewModel loginViewModel;

    public UserSignupPresenter(final UserSignupViewModel userSignupViewModel,
                               final ViewManagerModel viewManagerModel) {
        this.userSignupViewModel = userSignupViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(UserSignupOutputData userSignupOutputData) {
        // switch to login view
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final UserSignupState signupState = userSignupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        userSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        // TODO viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
