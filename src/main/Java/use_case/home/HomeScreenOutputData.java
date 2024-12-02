package use_case.home;

import entity.Event;

import java.util.List;

public class HomeScreenOutputData {
    private final boolean isEventPoster;
    private final List<Event> events;

    public HomeScreenOutputData(boolean isEventPoster, List<Event> events) {
        this.isEventPoster = isEventPoster;
        this.events = events;
    }

    public boolean isEventPoster() {
        return isEventPoster;
    }

    public List<Event> getEvents() {
        return events;
    }
}
