package use_case.delete_event;

import entity.Event;

import java.util.List;

/**
 * Represents the output data for the Delete Event use case.
 * Encapsulates the list of events and the status of the use case execution.
 */
public class DeleteEventOutputData {
    private List<Event> events;
    private boolean useCaseFailed;

    /**
     * Constructs a new DeleteEventOutputData instance with the specified list of events
     * and the status of the use case.
     *
     * @param events       the list of events remaining after deletion.
     * @param useCaseFailed a boolean indicating if the use case execution failed.
     */
    public DeleteEventOutputData(List<Event> events, boolean useCaseFailed) {
        this.events = events;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the list of events remaining after the deletion operation.
     *
     * @return the list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Returns whether the use case execution failed.
     *
     * @return {@code true} if the use case failed, {@code false} otherwise.
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
