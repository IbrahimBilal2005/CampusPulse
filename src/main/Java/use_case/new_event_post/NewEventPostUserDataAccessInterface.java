package use_case.new_event_post;

import entity.Event;

public interface NewEventPostUserDataAccessInterface {

    /**
     * Checks if the given event exists.
     * @param Eventname the event to look for
     * @return true if an event with the given exists; false otherwise
     */

    //checks if event exists
    boolean existsByName(String Eventname);


    /**
     * Adds event to given username
     */

    void addtoMyevents(Event event, String username);


}
