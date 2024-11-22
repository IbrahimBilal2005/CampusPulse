package use_case.search;

import entity.Event;
import entity.ICatagory;

import java.util.List;

/**
 * Interface for accessing and retrieving data for the Search use case.
 * It provides methods to search for events based on user interests or other criteria.
 */
public interface SearchDataAccessInterface {

    /**
     * Searches for events that match the specified criteria.
     *
     * @param criteria an object or map containing search filters (e.g., keywords, location, date range).
     * @return a list of events matching the search criteria.
     */
    List<Event> searchEvents(String criteria);

    /**
     * Retrieves all events in the system (optional, depending on your use case).
     *
     * @return a list of all events.
     */
    List<Event> getAllEvents();
}
