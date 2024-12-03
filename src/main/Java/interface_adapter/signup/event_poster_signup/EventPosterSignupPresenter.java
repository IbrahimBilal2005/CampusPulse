package interface_adapter.signup.event_poster_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.event_poster_signup.EventPosterSignupOutputBoundary;
import use_case.signup.event_poster_signup.EventPosterSignupOutputData;

public class EventPosterSignupPresenter implements EventPosterSignupOutputBoundary {
    private final EventPosterSignupViewModel eventPosterSignupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public EventPosterSignupPresenter(final EventPosterSignupViewModel eventPosterSignupViewModel,
                                      final ViewManagerModel viewManagerModel,
                                      final LoginViewModel loginViewModel) {
        this.eventPosterSignupViewModel = eventPosterSignupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView(EventPosterSignupOutputData eventPosterSignupOutputData) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(eventPosterSignupOutputData.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final EventPosterSignupState signupState = eventPosterSignupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        eventPosterSignupViewModel.firePropertyChanged();
    }
}
