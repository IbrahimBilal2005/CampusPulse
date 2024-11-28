package interface_adapter.filter;

import use_case.filter.FilterInputBoundary;
import use_case.filter.FilterInputData;

import java.time.LocalDateTime;
import java.util.List;

public class FilterController {
    private final FilterInputBoundary filterInteractor;

    public FilterController(FilterInputBoundary filterInteractor) {
        this.filterInteractor = filterInteractor;
    }

    public void executeFilter(Integer duration, List<String> tags, String location) {
        FilterInputData inputData = new FilterInputData(duration, location, tags);
        filterInteractor.filter(inputData);
    }
}