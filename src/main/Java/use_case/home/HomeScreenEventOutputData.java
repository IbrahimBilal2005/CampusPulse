package use_case.home;

import entity.Event;

public class HomeScreenEventOutputData {
    private final Event event;

    public HomeScreenEventOutputData(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
