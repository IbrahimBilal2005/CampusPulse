package use_case.search;

import entity.Event;

import java.util.List;

/**
 * This class represents the output data for the Search use case.
 * It contains the results of the search operation and the status of the operation.
 */
public class SearchOutputData {

    private final List<Event> events;

    /**
     * Constructs a new {@code SearchOutputData} instance with the specified details.
     *
     * @param events       the list of events that match the search query.
     */
    public SearchOutputData(List<Event> events) {
        this.events = events;
    }

    /**
     * Returns the list of events matching the search query.
     *
     * @return a list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

}
