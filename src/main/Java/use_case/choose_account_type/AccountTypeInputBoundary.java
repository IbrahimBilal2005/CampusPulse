package use_case.choose_account_type;

/**
 * Input Boundary for actions related to selecting an account type.
 */
public interface AccountTypeInputBoundary {

    /**
     * Executes the switch to General User Signup View use case.
     */
    void switchToGeneralUserSignupView();

    /**
     * Executes the switch to Event Poster Signup View use case.
     */
    void switchToEventPosterSignupView();
}
