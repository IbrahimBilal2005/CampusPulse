package entity;

/**
 * EventPoster User type interface
 */
public interface EventPosterInterface extends UserInterface {

    void createEvent(Event event);
    void deleteEvent(int eventID);
}

