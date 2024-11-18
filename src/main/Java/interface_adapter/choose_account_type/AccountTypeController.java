package interface_adapter.choose_account_type;

import use_case.signup.choose_account_type.AccountTypeInputBoundary;

public class AccountTypeController {
    private final AccountTypeInputBoundary userAccountTypeUseCaseInteractor;

    public AccountTypeController(AccountTypeInputBoundary userAccountTypeUseCaseInteractor) {
        this.userAccountTypeUseCaseInteractor = userAccountTypeUseCaseInteractor;
    }

    /**
     * Execute the "switch to General User Signup View" Use Case.
     */
    public void switchToGeneralUserSignupView() {
        userAccountTypeUseCaseInteractor.switchToGeneralUserSignupView();
    }

    /**
     * Execute the "switch to Event Poster Signup View" Use Case.
     */
    public void switchToEventPosterSignupView() {
        userAccountTypeUseCaseInteractor.switchToEventPosterSignupView();
    }

    /**
     * Execute the "switch to Base View" Use Case.
     */
    public void switchToBaseView() {
        userAccountTypeUseCaseInteractor.switchToBaseView();
    }
}
