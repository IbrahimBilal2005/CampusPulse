package use_case.event_details;

/**
 * The Input Data for the event details use case
 * However since there is no required input data it is blank
 */
public class EventDetailsInputData {
    private final boolean click;

    public EventDetailsInputData(boolean click) {
        this.click = click;
    }

    public boolean getClick() { return click; }
}
