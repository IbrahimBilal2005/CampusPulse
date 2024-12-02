package use_case.new_event_post;

import entity.Event;

public interface NewEventPostUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param Eventname the username to look for
     * @return true if a user with the given username exists; false otherwise
     */

    //checks if event exists
    boolean existsByName(String Eventname);

    //adds event to mongo and internal memory
    void addEvent(Event event);


}
