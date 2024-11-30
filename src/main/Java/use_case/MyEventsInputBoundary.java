package use_case;

import entity.Event;

import java.util.List;

public interface MyEventsInputBoundary {
    List<Event> getMyEvents();
    void editEvent(Event event);
    void deleteEvent(Event event);
}