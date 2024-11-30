package interface_adapter.delete_event;

import entity.Event;

import java.util.List;

public class MyEventsState {
    private List<Event> events;

    public MyEventsState(MyEventsState copy) {
        this.events = copy.events;
    }

    public MyEventsState() {}

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
