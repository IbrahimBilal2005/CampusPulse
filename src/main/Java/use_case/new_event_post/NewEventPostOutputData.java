package use_case.new_event_post;

import entity.Event;

/**
 * The Output Data for the New Event Post Use Case.
 */

public class NewEventPostOutputData {
    private final Event newevent;

    public NewEventPostOutputData(Event newevent) {
        this.newevent = newevent;
    }


    public Event getNewevent() {
        return newevent;
    }

}
