package interface_adapter.delete_event;

import entity.Event;

import java.util.ArrayList;
import java.util.List;

public class MyEventsState {
    private List<Event> events = new ArrayList<>();
    private String error = "";

    public MyEventsState(MyEventsState copy) {
        this.events = copy.events;
        this.error = copy.error;
    }

    public MyEventsState() {}
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
