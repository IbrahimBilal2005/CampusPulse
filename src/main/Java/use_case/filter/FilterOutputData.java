package use_case.filter;

import entity.Event;

import java.util.List;

public class FilterOutputData {
    private final List<Event> events;

    public FilterOutputData(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}