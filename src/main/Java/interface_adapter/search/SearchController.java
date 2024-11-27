package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {

    private final SearchInputBoundary inputBoundary;

    public SearchController(SearchInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void search(String query) {
        SearchInputData inputData = new SearchInputData(query);
        inputBoundary.search(inputData);
    }
}
