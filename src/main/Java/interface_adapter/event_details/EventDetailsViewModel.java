package interface_adapter.event_details;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

public class EventDetailsViewModel extends ViewModel<LoginState> {

    public final String DATE = "Date: ";
    public final String LOCATION = "Location: ";
    public final String TIME = "Time: ";
    public final String TAGS = "Tags: ";
    public final String TITLE = "CampusPulse";
    public final String BACK = "Back";
    // public final String TEST = "JAN 18 2025, 4:00PM - 8:00PM";

    public EventDetailsViewModel() {
        super("EventDetails_screen");
        //setState(new LoginState());
    }
}
