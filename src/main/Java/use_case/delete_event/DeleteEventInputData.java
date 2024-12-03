package use_case.delete_event;

import entity.Event;

/**
 * Represents the input data for the Delete Event use case.
 * This class encapsulates the username of the event poster
 * and the event that needs to be deleted.
 */
public class DeleteEventInputData {

    private final String username;
    private final Event eventToDelete;

    /**
     * Constructs a DeleteEventInputData object with the specified username and event.
     *
     * @param username      the username of the EventPoster requesting the deletion.
     * @param eventToDelete the Event to be deleted.
     */
    public DeleteEventInputData(String username, Event eventToDelete) {
        this.username = username;
        this.eventToDelete = eventToDelete;
    }

    /**
     * Returns the username of the EventPoster requesting the deletion.
     *
     * @return the username as a String.
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the Event that needs to be deleted.
     *
     * @return the Event object to delete.
     */
    Event getEventToDelete() {
        return eventToDelete;
    }
}
