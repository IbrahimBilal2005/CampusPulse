package use_case.search;

import entity.Event;
import use_case.search.SearchOutputBoundary;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface dataAccess;
    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface dataAccess, SearchOutputBoundary searchPresenter) {
        this.dataAccess = dataAccess;
        this.searchPresenter = searchPresenter;
    }

    @Override
    public void search(SearchInputData inputData) {
        String query = inputData.getQuery().trim();

        // Check if the query is empty
        if (query.isEmpty()) {
            // If the query is empty, show all events
            List<Event> allEvents = dataAccess.getAllEvents();
            if (allEvents.isEmpty()) {
                searchPresenter.setFailView("No events available.");
            } else {
                searchPresenter.setPassView(new SearchOutputData(allEvents));
            }
            return;
        }

        // Get events based on the search query
        List<Event> events = dataAccess.searchEvents(query);

        // If no events are found, return an error message
        if (events.isEmpty()) {
            searchPresenter.setFailView("No events found matching your search query.");
        } else {
            searchPresenter.setPassView(new SearchOutputData(events));
        }
    }
}
