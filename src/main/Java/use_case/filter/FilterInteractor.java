package use_case.filter;

import data_access.EventDAO; // Assuming you have an EventDAO for data access
import entity.Event;
import use_case.search.SearchDataAccessInterface;

import java.util.List;
import java.util.Map;

public class FilterInteractor implements FilterInputBoundary {
    private final FilterDataAccessInterface dataAccess;
    private final FilterOutputBoundary outputBoundary;

    public FilterInteractor(FilterDataAccessInterface dataAccess, FilterOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void filter(FilterInputData inputData) {
        // Extract filtering criteria as a simple map or individual parameters
        Map<String, Object> filterCriteria = Map.of(
                "startTime", inputData.getStartTime(),
                "location", inputData.getLocation(),
                "tags", inputData.getTags()
        );

        // Use the transformed criteria in the data access layer
        List<Event> filteredEvents = dataAccess.filterEvents(filterCriteria);

        // Provide the output through the boundary
        if (!filteredEvents.isEmpty()) {
            outputBoundary.setPassView(new FilterOutputData(filteredEvents));
        } else {
            outputBoundary.setFailView("No events found matching the filter criteria.");
        }
    }
}