package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeScreenState;
import interface_adapter.home.HomeScreenViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * Presenter for Login Use Case
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeScreenViewModel homeScreenViewModel;

    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, HomeScreenViewModel homeScreenViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeScreenViewModel = homeScreenViewModel;
    }

    // Switches the view after the user succesfully logs in
    @Override
    public void prepareSuccessView(LoginOutputData outputData) {
        if (!outputData.isUseCaseFailed()) {
            final HomeScreenState homeScreenState = homeScreenViewModel.getState();
            this.homeScreenViewModel.setState(homeScreenState);
            this.viewManagerModel.setState(homeScreenViewModel.getViewName());
        }
    }

    // Provides error message containing whether username or password is invallid
    @Override
    public void prepareFailView(String errorMessage) {
        LoginState state = loginViewModel.getState();
        state.setLoginError(errorMessage);
        loginViewModel.setState(state);
    }
}
