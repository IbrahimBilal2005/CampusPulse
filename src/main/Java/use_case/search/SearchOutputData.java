package use_case.search;

import entity.Event;

import java.util.List;

/**
 * This class represents the output data for the Search use case.
 * It contains the results of the search operation and the status of the operation.
 */
public class SearchOutputData {

    private final List<Event> events;
    private final boolean isFailure;
    private final String errorMessage;

    /**
     * Constructs a new {@code SearchOutputData} instance with the specified details.
     *
     * @param events       the list of events that match the search query.
     * @param isFailure    {@code true} if the search operation failed, {@code false} otherwise.
     * @param errorMessage a message describing the error, or {@code null} if the operation succeeded.
     */
    public SearchOutputData(List<Event> events, boolean isFailure, String errorMessage) {
        this.events = events;
        this.isFailure = isFailure;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the list of events matching the search query.
     *
     * @return a list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Returns whether the search operation failed.
     *
     * @return {@code true} if the search operation failed, {@code false} otherwise.
     */
    public boolean isFailure() {
        return isFailure;
    }

    /**
     * Returns the error message, or {@code null} if the operation succeeded.
     *
     * @return the error message or {@code null}.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
