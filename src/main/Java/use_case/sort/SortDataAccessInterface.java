package use_case.sort;

import entity.Event;

import java.util.List;

/**
 * Interface for accessing and retrieving data for the Sort use case.
 * It provides methods to sort events based on time location etc.
 */
public interface SortDataAccessInterface {

    List<Event> getAllEvents();
}