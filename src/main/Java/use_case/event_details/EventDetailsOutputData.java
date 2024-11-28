package use_case.event_details;

public class EventDetailsOutputData {
    private final boolean click;


    public EventDetailsOutputData(boolean click) {
        this.click = click;
    }

    public boolean isClick() {
        return click;
    }
}
