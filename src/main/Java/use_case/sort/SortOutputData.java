package use_case.sort;

import entity.Event;

import java.util.List;

/**
 * The output data for the sort use case.
 */
public class SortOutputData {
    private final List<Event> sortedEvents;

    /**
     * Constructs a SortOutputData based on the specified sortedEvents list
     * @param sortedEvents the sorted events.
     */
    public SortOutputData(List<Event> sortedEvents) {
        this.sortedEvents = sortedEvents;
    }

    /**
     * Get the sorted events.
     * @return List of sorted events.
     */
    public List<Event> getSortedEvents() { return sortedEvents; }
}

