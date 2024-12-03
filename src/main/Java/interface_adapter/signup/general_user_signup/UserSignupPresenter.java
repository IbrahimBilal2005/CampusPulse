package interface_adapter.signup.general_user_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.general_user_signup.UserSignupOutputBoundary;
import use_case.signup.general_user_signup.UserSignupOutputData;

public class UserSignupPresenter implements UserSignupOutputBoundary {
    private final UserSignupViewModel userSignupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserSignupPresenter(ViewManagerModel viewManagerModel,
                               UserSignupViewModel userSignupViewModel,
                               LoginViewModel loginViewModel) {

        this.userSignupViewModel = userSignupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView(UserSignupOutputData userSignupOutputData) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(userSignupOutputData.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final UserSignupState signupState = userSignupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        userSignupViewModel.firePropertyChanged();
    }
}
