package use_case.sort;

import entity.Event;

import java.util.List;

/**
 * Input data for sort use case.
 */
public class SortInputData {
    private String sortQuery;
    private List<Event> filteredEvents;

    public SortInputData(String sortQuery, List<Event> filteredEvents) {
        this.sortQuery = sortQuery;
        this.filteredEvents = filteredEvents;
    }

    public String getSortQuery() { return sortQuery; }
    public List<Event> getFilteredEvents() { return filteredEvents; }
}