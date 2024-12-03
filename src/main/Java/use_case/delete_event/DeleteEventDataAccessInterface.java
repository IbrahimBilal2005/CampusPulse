package use_case.delete_event;

import entity.Event;
import entity.EventPoster;

import java.util.List;

/**
 * The interface of the Data Access Object (DAO) for the Delete Event use case.
 * This interface provides methods to interact with the data layer for deleting events
 * and managing event posters.
 */
public interface DeleteEventDataAccessInterface {

    /**
     * Retrieves the EventPoster associated with the specified username.
     *
     * @param username the username of the event poster.
     * @return the EventPoster object associated with the username.
     */
    EventPoster getUser(String username);

    /**
     * Deletes the specified event from the EventPoster's list of events.
     *
     * @param eventPoster  the EventPoster who owns the event.
     * @param eventToDelete the Event to be deleted.
     */
    void deleteEvent(EventPoster eventPoster, Event eventToDelete);

    /**
     * Checks if the specified event exists for the given username.
     *
     * @param username the username of the EventPoster.
     * @param event    the Event to check for existence.
     * @return true if the event exists for the specified user, false otherwise.
     */
    boolean eventExists(String username, Event event);

    /**
     * Adds the specified EventPoster account to the data source.
     *
     * @param eventPoster the EventPoster to be added.
     */
    void addAccount(EventPoster eventPoster);
}
