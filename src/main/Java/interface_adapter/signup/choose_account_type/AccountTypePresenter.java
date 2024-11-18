package interface_adapter.signup.choose_account_type;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.event_poster_signup.EventPosterSignupViewModel;
import interface_adapter.signup.general_user_signup.UserSignupViewModel;
import use_case.signup.choose_account_type.AccountTypeOutputBoundary;

public class AccountTypePresenter implements AccountTypeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final EventPosterSignupViewModel eventPosterSignupViewModel;
    private final UserSignupViewModel userSignupViewModel;

    public AccountTypePresenter(ViewManagerModel viewManagerModel,
                                EventPosterSignupViewModel eventPosterSignupViewModel,
                                UserSignupViewModel userSignupViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.eventPosterSignupViewModel = eventPosterSignupViewModel;
        this.userSignupViewModel = userSignupViewModel;
    }

    @Override
    public void switchToGeneralUserSignupView() {
        viewManagerModel.setState(userSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEventPosterSignupView() {
        viewManagerModel.setState(eventPosterSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBaseView() {

    }
}
