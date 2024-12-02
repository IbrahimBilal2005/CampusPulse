package interface_adapter.home;

import entity.Event;

import java.util.List;

public class HomeScreenViewModel {
    private boolean isEventPoster;
    private List<Event> events;

    public boolean isEventPoster() {
        return isEventPoster;
    }

    public void setEventPoster(boolean eventPoster) {
        isEventPoster = eventPoster;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
