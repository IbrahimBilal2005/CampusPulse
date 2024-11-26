package use_case.search;

import entity.Event;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface dataAccess;

    public SearchInteractor(SearchDataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void search(SearchInputData inputData) {
        String query = inputData.getQuery().trim();

        // Check if the query is empty
        if (query.isEmpty()) {
            // If the query is empty, show all events
            List<Event> allEvents = dataAccess.getAllEvents();
            if (allEvents.isEmpty()) {
                presentError("No events available.");
            } else {
                presentSearchResults(allEvents);
            }
            return;
        }

        // Get events based on the search query
        List<Event> events = dataAccess.searchEvents(query);

        // If no events are found, return an error message
        if (events.isEmpty()) {
            presentError("No events found matching your search query.");
        } else {
            presentSearchResults(events);
        }
    }

    private void presentSearchResults(List<Event> events) {
        // This method is where you'd send the results to the view or presenter
        // For example, you might pass the events to a presenter or return them to the controller
        System.out.println("Search Results:");
        for (Event event : events) {
            System.out.println(event.getName()); // Or whatever you'd like to do with the events
        }
    }

    private void presentError(String message) {
        // This method will display the error message to the user
        System.out.println("Error: " + message);
    }
}
