package use_case.delete_event;

import entity.Event;

import java.util.Map;

/**
 * Output Data for the Delete Event use case.
 */
public class DeleteEventOutputData {
    private Map<String, Event> events;
    private boolean useCaseFailed;

    public DeleteEventOutputData(Map<String, Event> events, boolean useCaseFailed) {
        this.events = events;
        this.useCaseFailed = useCaseFailed;
    }

    public Map<String, Event> getEvents() { return events; }
    public boolean isUseCaseFailed() { return useCaseFailed; }
}
