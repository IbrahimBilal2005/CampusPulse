package use_case.choose_account_type;

public class AccountTypeInteractor implements AccountTypeInputBoundary{
    private final AccountTypeUserDataAccessInterface userDataAccessObject;
    private final AccountTypeOutputBoundary userPresenter;

    public AccountTypeInteractor(AccountTypeUserDataAccessInterface userDataAccessObject,
                                 AccountTypeOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
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
}
