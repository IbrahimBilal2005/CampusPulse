package use_case.sort;

import entity.Event;

import java.util.List;

/**
 * Input data for sort use case.
 */
public class SortInputData {
    private String sortQuery;
    private List<Event> currentEvents;

    public SortInputData(String sortQuery, List<Event> currentEvents) {
        this.sortQuery = sortQuery;
        this.currentEvents = currentEvents;
    }

    public String getSortQuery() { return sortQuery; }
    public List<Event> getCurrentEvents() { return currentEvents; }
}