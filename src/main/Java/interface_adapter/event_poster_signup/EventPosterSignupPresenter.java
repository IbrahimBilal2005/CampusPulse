package interface_adapter.event_poster_signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.event_poster_signup.EventPosterSignupOutputBoundary;
import use_case.signup.event_poster_signup.EventPosterSignupOutputData;

public class EventPosterSignupPresenter implements EventPosterSignupOutputBoundary {
    private final EventPosterSignupViewModel eventPosterSignupViewModel;
    private final ViewManagerModel viewManagerModel;
    // TODO private final LoginViewModel loginViewModel;

    public EventPosterSignupPresenter(final EventPosterSignupViewModel eventPosterSignupViewModel,
                                      final ViewManagerModel viewManagerModel) {
        this.eventPosterSignupViewModel = eventPosterSignupViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(EventPosterSignupOutputData eventPosterSignupOutputData) {
        // switch to login view
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final EventPosterSignupState signupState = eventPosterSignupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        eventPosterSignupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        // TODO viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBaseView() {
        // TODO viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
