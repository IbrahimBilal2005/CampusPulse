package use_case.search;

import entity.Event;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface dataAccess;
    private final SearchOutputBoundary searchOutPut;

    public SearchInteractor(SearchDataAccessInterface dataAccess, SearchOutputBoundary searchPresenter) {
        this.dataAccess = dataAccess;
        this.searchOutPut = searchPresenter;
    }

    @Override
    public void search(SearchInputData inputData) {
        String query = inputData.getQuery().trim();

        // Check if the query is empty
        if (query.isEmpty()) {
            // If the query is empty, show all events
            List<Event> allEvents = dataAccess.getAllEvents();
            if (allEvents.isEmpty()) {
                searchOutPut.setFailView("No events available.");
            } else {
                // Pass the events to the presenter without calling setFailView
                searchOutPut.setPassView(new SearchOutputData(allEvents)); // Use SearchOutputData to wrap events
            }
            return;
        }

        // Get events based on the search query
        List<Event> events = dataAccess.searchEvents(query);

        // If no events are found, return an error message
        if (events.isEmpty()) {
            searchOutPut.setFailView("No events found matching your search query.");
        } else {
            searchOutPut.setPassView(new SearchOutputData(events)); // Use SearchOutputData to wrap events
        }
    }
}
