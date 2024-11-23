package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.List;
import java.util.Map;

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void search(String keyword, Map<String, String> filters) {
        // Assuming keyword represents category and filters contains tags
        String category = keyword;
        List<String> tags = List.of(filters.get("tags").split(","));

        SearchInputData inputData = new SearchInputData(category, tags);
        interactor.search(inputData);
    }
}
