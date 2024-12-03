package interface_adapter.home;

import entity.Event;

import java.util.List;

public class HomeScreenState {
    private boolean isEventPoster; // Whether the user is an Event Poster
    private List<Event> events; // List of events to display on the home screen
    private Event selectedEvent; // The event currently being viewed in detail

    // Getter and setter for isEventPoster
    public boolean isEventPoster() {
        return isEventPoster;
    }

    public void setEventPoster(boolean isEventPoster) {
        this.isEventPoster = isEventPoster;
    }

    // Getter and setter for events
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // Getter and setter for selectedEvent
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
}
