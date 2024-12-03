package interface_adapter.event_details;

import interface_adapter.ViewModel;

/**
 * View model for Event Details
 */
public class EventDetailsViewModel extends ViewModel<EventDetailsState> {

    public EventDetailsViewModel() {
        super("EventDetails_screen");
        setState(new EventDetailsState());
    }
}
