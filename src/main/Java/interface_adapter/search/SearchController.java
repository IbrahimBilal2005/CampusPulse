package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.Map;

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void search(String keyword, Map<String, String> filters) {
        SearchInputData inputData = new SearchInputData(keyword);
        interactor.search(inputData);
    }
}
