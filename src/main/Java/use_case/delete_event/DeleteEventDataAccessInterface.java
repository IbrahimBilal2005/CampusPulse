package use_case.delete_event;

import entity.Account;
import entity.Event;
import entity.EventPoster;

import java.util.List;

/**
 * The interface of the DAO for the Delete Event use case.
 */
public interface DeleteEventDataAccessInterface {

    Account getAccountByUsername(String username);

    void deleteEvent(EventPoster eventPoster, Event eventToDelete);

    boolean eventExists(String username, Event event);

    void addAccount(EventPoster eventPoster);
}
