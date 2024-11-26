package use_case.search;

import entity.Event;
import java.util.List;

/**
 * Interface for accessing and retrieving data for the Search use case.
 * It provides methods to search for events based on user interests or other criteria.
 */
public interface SearchDataAccessInterface {

    /**
     * Searches for events that match the specified criteria.
     *
     * @param query the phrase searched by the user.
     * @return a list of events matching the search criteria.
     */
    List<Event> searchEvents(String query);

    /**
     * Retrieves all events in the system (optional, depending on your use case).
     *
     * @return a list of all events.
     */
    List<Event> getAllEvents();
}
