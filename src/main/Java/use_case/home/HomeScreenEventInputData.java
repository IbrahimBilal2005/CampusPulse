package use_case.home;

import entity.Event;

public class HomeScreenEventInputData {
    private final Event event;

    public HomeScreenEventInputData(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
