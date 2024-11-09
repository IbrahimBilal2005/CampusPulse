package entity.Interfaces;

import entity.Event;

/**
 * EventPoster User type interface
 */
public interface EventPosterInterface extends UserInterface {

    void createEvent(Event event);
    void deleteEvent(int eventID);
}

