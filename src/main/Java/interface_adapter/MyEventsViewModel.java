
package interface_adapter;

import entity.Event;
import use_case.my_events.MyEventsInputBoundary;
import use_case.my_events.MyEventsOutputBoundary;

import java.util.List;

public class MyEventsViewModel implements MyEventsOutputBoundary {
    private final MyEventsInputBoundary interactor;
    private List<Event> events;

    public MyEventsViewModel(MyEventsInputBoundary interactor) {
        this.interactor = interactor;
        loadEvents();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void loadEvents() {
        events = interactor.getMyEvents();
    }

    public void editEvent(Event event) {
        // Logic to edit the event
        interactor.editEvent(event);
        loadEvents(); // Refresh the event list
    }

    public void deleteEvent(Event event) {
        interactor.deleteEvent(event);
        loadEvents(); // Refresh the event list
    }

    @Override
    public void presentEvents(List<Event> events) {
        this.events = events;
    }
}