package use_case.event_details;

/**
 * Output data for the event details use case
 */
public class EventDetailsOutputData {

    private final boolean click;

    public EventDetailsOutputData(boolean click) {
        this.click = click;
    }

    // Returns the value of click
    public boolean isClick() {
        return click;
    }
}
