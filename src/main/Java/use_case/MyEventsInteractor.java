package use_case;

import entity.Event;
import entity.EventPoster; // Assuming EventPoster is the user type that has events
import use_case.my_events.MyEventsOutputBoundary;

import java.util.List;

public class MyEventsInteractor implements MyEventsInputBoundary {
    private final MyEventsOutputBoundary outputBoundary;
    private final EventPoster eventPoster; // The current user

    public MyEventsInteractor(MyEventsOutputBoundary outputBoundary, EventPoster eventPoster) {
        this.outputBoundary = outputBoundary;
        this.eventPoster = eventPoster;
    }

    @Override
    public List<Event> getMyEvents() {
        List<Event> events = eventPoster.getEvents(); // Get events from the current user
        outputBoundary.presentEvents(events);
        return events;
    }

    @Override
    public void editEvent(Event event) {
        // Logic to edit the event
        // This might involve updating the event in a database or in-memory storage
    }

    @Override