package use_case.delete_event;

import entity.EventPoster;

/**
 * The interface of the DAO for the Delete Event use case.
 */
public interface DeleteEventDataAccessInterface {

    EventPoster getUser(String username);

    void deleteEvent(EventPoster eventPoster, String eventToDelete);
}
