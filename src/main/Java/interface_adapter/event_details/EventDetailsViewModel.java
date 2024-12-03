package interface_adapter.event_details;

import interface_adapter.ViewModel;

/**
 * View model for Event Details
 */
public class EventDetailsViewModel extends ViewModel<EventDetailsState> {

    public final String DATE = "Date: ";
    public final String LOCATION = "Location: ";
    public final String TIME = "Time: ";
    public final String TAGS = "Tags: ";
    public final String BACK = "Back";

    public EventDetailsViewModel() {
        super("EventDetails_screen");
        setState(new EventDetailsState());
    }
}
