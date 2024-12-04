package use_case.new_event_post;

import entity.Event;

/**
 * The data access interface for the New Event Post Use Case.
 */

public interface NewEventPostDataAccessInterface {

    /**
     * Checks if the given event exists.
     * @param eventName the event to look for
     * @return true if an event with the given exists; false otherwise
     */

    //checks if event exists
    boolean existsByName(String eventName);



    /**
     * Adds event to database.
     */

    void addEvents(Event event);
}
