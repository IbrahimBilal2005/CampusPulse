package interface_adapter.choose_account_type;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Decision View.
 */
public class AccountTypeViewModel extends ViewModel<AccountTypeState> {

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String EVENT_POSTER_BUTTON_LABEL = "Event Poster";
    public static final String GENERAL_USER_BUTTON_LABEL = "General User";
    public static final String TITLE_LABEL = "Choose Account Type";

    public AccountTypeViewModel() {
        super("Signup Decision Form");
        setState(new AccountTypeState());
    }
}
