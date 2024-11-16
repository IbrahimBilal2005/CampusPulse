package interface_adapter.event_poster_signup;

import interface_adapter.ViewModel;

public class EventPosterSignupViewModel extends ViewModel<EventPosterSignupState> {

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TITLE_LABEL = "Event Poster Signup";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm Password";
    public static final String ORGANIZATION_NAME_LABEL = "Organization Name";
    public static final String SOP_LINK_LABEL = "SOP Link";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String NEXT_BUTTON_LABEL = "Next" ;
    public static final String BACK_BUTTON = "Back";

    public EventPosterSignupViewModel() {
        super("Event Poster Signup Screen");
        setState(new EventPosterSignupState());
    }
}
