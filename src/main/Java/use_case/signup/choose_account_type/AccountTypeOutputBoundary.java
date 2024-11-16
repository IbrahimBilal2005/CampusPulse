package use_case.signup.choose_account_type;

/**
 * The output boundary for the Pick Account Type Use Case.
 */
public interface AccountTypeOutputBoundary {

    /**
     * Switches to the General User Signup View.
     */
    void switchToGeneralUserSignupView();

    /**
     * Switches to the Event Poster Signup View.
     */
    void switchToEventPosterSignupView();
}

