package interface_adapter.delete_event;

import entity.Event;

import java.util.Map;

public class MyEventsState {
    private Map<String, Event> events;

    public MyEventsState(MyEventsState copy) {
        this.events = copy.events;
    }

    public MyEventsState() {}

    public Map<String, Event> getEvents() { return events; }
    public void setEvents(Map<String, Event> events) { this.events = events; }
}
