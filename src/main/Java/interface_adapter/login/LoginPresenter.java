package interface_adapter.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    public LoginPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        if (!outputData.isUseCaseFailed()) {
//      TODO viewManagerModel.setState(homeViewModel.getViewName());
        }
    }

    @Override
    public void prepareFailView(String errorMessage) {
        LoginState state = loginViewModel.getState();
        state.setLoginError(errorMessage);
        loginViewModel.setState(state);
    }
}
