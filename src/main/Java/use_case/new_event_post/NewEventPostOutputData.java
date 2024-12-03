package use_case.new_event_post;

import entity.Event;

/**
 * The Output Data for the New Event Post Use Case.
 */

public class NewEventPostOutputData {
    private final boolean useCaseFailed; // Indicates if the use case failed
    private final Event newevent;

    public NewEventPostOutputData(boolean useCaseFailed, Event newevent) {
        this.useCaseFailed = useCaseFailed;
        this.newevent = newevent;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public Event getNewevent() {
        return newevent;
    }

}
