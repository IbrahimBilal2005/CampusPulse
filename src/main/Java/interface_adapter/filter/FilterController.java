package interface_adapter.filter;

import entity.Event;
import use_case.filter.FilterInputBoundary;
import use_case.filter.FilterInputData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FilterController {
    private final FilterInputBoundary filterInteractor;

    public FilterController(FilterInputBoundary filterInteractor) {
        this.filterInteractor = filterInteractor;
    }

    public void executeFilter(Map<String, Object> filters, List<Event> events) {
        FilterInputData inputData = new FilterInputData((Integer) filters.get("duration"), (String) filters.get("location"), (List<String>) filters.get("tags"), events);
        filterInteractor.filter(inputData);
    }
}