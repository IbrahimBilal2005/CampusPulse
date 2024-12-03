package use_case.new_event_post;

import entity.Event;

/**
 * The data access interface for the New Event Post Use Case.
 */

public interface NewEventPostUserDataAccessInterface {

    /**
     * Adds event to given username
     */

    void addtoMyevents(Event event, String username);
}
