package use_case.sort;

import entity.Event;

import java.util.List;

/**
 * Input data for sort use case.
 */
public class SortInputData {
    private String sortQuery;
    private List<Event> currentEvents;

    /**
     * Constructs a SortInputData with the specified query and currentEvents.
     * @param sortQuery the query to determine how to sort.
     * @param currentEvents the currentEvents to sort.
     */
    public SortInputData(String sortQuery, List<Event> currentEvents) {
        this.sortQuery = sortQuery;
        this.currentEvents = currentEvents;
    }

    /**
     * Get the sort query.
     * @return sort query.
     */
    public String getSortQuery() { return sortQuery; }

    /**
     * Get the list of current events that the sort use case needs to sort.
     * @return the list of current events.
     */
    public List<Event> getCurrentEvents() { return currentEvents; }
}