package use_case.search;

import entity.Event;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary {

    private final SearchDataAccessInterface searchDAO;
    private final SearchOutputBoundary outputBoundary;

    public SearchInteractor(SearchDataAccessInterface searchDAO, SearchOutputBoundary outputBoundary) {
        this.searchDAO = searchDAO;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void search(SearchInputData inputData) {
        List<Event> events = searchDAO.searchEvents(inputData.getCategory());
        if (events.isEmpty()) {
            outputBoundary.setFailView("No events found");
        } else {
            SearchOutputData outputData = new SearchOutputData(events, false, null);
            outputBoundary.setPassView(outputData);
        }
    }
}
