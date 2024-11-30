package use_case.delete_event;

import entity.Event;
import entity.EventPoster;

import java.util.List;

/**
 * The interface of the DAO for the Delete Event use case.
 */
public interface DeleteEventDataAccessInterface {

    EventPoster getUser(String username);

    void deleteEvent(EventPoster eventPoster, String eventToDelete);
}
