package interface_adapter.signup.event_poster_signup;

import interface_adapter.ViewModel;

public class EventPosterSignupViewModel extends ViewModel<EventPosterSignupState> {

    public static final String ORGANIZATION_NAME_LABEL = "Organization Name";
    public static final String SOP_LINK_LABEL = "SOP Link";
    public static final String TITLE_LABEL = "Event Poster Signup";

    public EventPosterSignupViewModel() {
        super("Event Poster Signup Screen");
        setState(new EventPosterSignupState());
    }
}
