package interface_adapter.home;

import entity.Event;

import java.util.List;

public class HomeScreenViewModel {
    private boolean isEventPoster; // Whether the user is an Event Poster
    private List<Event> events; // Events to display on the Home Screen
    private Event selectedEvent; // The event selected by the user for detailed view

    // Getter and Setter for isEventPoster
    public boolean isEventPoster() {
        return isEventPoster;
    }

    public void setEventPoster(boolean eventPoster) {
        isEventPoster = eventPoster;
    }

    // Getter and Setter for events
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // Getter and Setter for selectedEvent
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    // Notify the UI to switch to the Event Details screen
    public void switchToEventDetailsScreen() {
        System.out.println("Switching to Event Details screen for event: " + selectedEvent.getName());
    }
}
