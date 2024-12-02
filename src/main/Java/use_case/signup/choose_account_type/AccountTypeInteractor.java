package use_case.signup.choose_account_type;

public class AccountTypeInteractor implements AccountTypeInputBoundary {
    private final AccountTypeOutputBoundary userPresenter;

    public AccountTypeInteractor(AccountTypeOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public void switchToGeneralUserSignupView() {
        userPresenter.switchToGeneralUserSignupView();
    }

    @Override
    public void switchToEventPosterSignupView() {
        userPresenter.switchToEventPosterSignupView();
    }

    @Override
    public void switchToBaseView() { userPresenter.switchToBaseView(); }
}
