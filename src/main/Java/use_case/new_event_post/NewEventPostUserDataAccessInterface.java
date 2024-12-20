package use_case.new_event_post;

import entity.Event;

/**
 * The data access interface for the New Event Post Use Case.
 */

public interface NewEventPostUserDataAccessInterface {

    /**
     * Checks if the given event exists.
     * @param eventName the event to look for
     * @return true if an event with the given exists; false otherwise
     */

    //checks if event exists
    boolean existsByName(String eventName);



    /**
     * Adds event to given username
     */

    void addtoMyevents(Event event, String username);
}
