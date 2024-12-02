package use_case.filter;

import entity.Event;
import java.util.List;
import java.util.Map;

/**
 * Interface for accessing and retrieving data for the Search use case.
 * It provides methods to search for events based on user interests or other criteria.
 */
public interface FilterDataAccessInterface {

    /**
     * Searches for events that match the specified criteria.
     *
     * @param filterCriteria the phrase searched by the user.
     * @return a list of events matching the search criteria.
     */
    List<Event> filterEvents(Map<String, Object> filterCriteria, List<Event> events);

    /**
     * Retrieves all events in the system (optional, depending on your use case).
     *
     * @return a list of all events.
     */
    List<Event> getAllEvents();
}
