package use_case.delete_event;

import entity.Event;

import java.util.List;

/**
 * Output Data for the Delete Event use case.
 */
public class DeleteEventOutputData {
    private List<Event> events;
    private boolean useCaseFailed;

    public DeleteEventOutputData(List<Event> events, boolean useCaseFailed) {
        this.events = events;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Event> getEvents() { return events; }
    public boolean isUseCaseFailed() { return useCaseFailed; }
}
