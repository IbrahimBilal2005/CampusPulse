package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void search(String keyword) {
        // Create SearchInputData with just the keyword (query)
        SearchInputData inputData = new SearchInputData(keyword);
        // Pass the input data to the interactor
        interactor.search(inputData);
    }
}
