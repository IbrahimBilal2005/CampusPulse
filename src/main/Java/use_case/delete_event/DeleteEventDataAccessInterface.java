package use_case.delete_event;

import entity.Account;
import entity.Event;
import entity.EventPoster;

import java.util.List;
import java.util.Map;

/**
 * The interface of the Data Access Object (DAO) for the Delete Event use case.
 * This interface provides methods to interact with the data layer for deleting events
 * and managing event posters.
 */
public interface DeleteEventDataAccessInterface {

    /**
     * Deletes the specified event from the EventPoster's list of events.
     *
     * @param username  the username for the event poster who owns the event.
     * @param eventToDelete the Event to be deleted.
     */
    void deleteEvent(String username, Event eventToDelete);

    /**
     * Get event poster's events
     *
     * @param username the username of the EventPoster.
     * @return true if the event exists for the specified user, false otherwise.
     */
    Map<String, Event> getUserEvents(String username);

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
