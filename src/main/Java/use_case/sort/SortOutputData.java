package use_case.sort;

import entity.Event;

import java.util.List;

public class SortOutputData {
    private final List<Event> sortedEvents;

    public SortOutputData(List<Event> sortedEvents) {
        this.sortedEvents = sortedEvents;
    }

    public List<Event> getSortedEvents() { return sortedEvents; }
}

