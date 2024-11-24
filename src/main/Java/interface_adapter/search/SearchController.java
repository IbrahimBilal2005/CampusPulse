package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void search(String keyword, Map<String, String> filters) {
        // Handle null filters and empty tags
        String category = keyword;
        List<String> tags = getTagsFromFilters(filters);

        // Create SearchInputData and pass it to the interactor
        SearchInputData inputData = new SearchInputData(category, tags);
        interactor.search(inputData);
    }

    private List<String> getTagsFromFilters(Map<String, String> filters) {
        if (filters == null || !filters.containsKey("tags")) {
            return Collections.emptyList(); // Return empty list if filters are null or no tags exist
        }

        String tagsString = filters.get("tags");
        if (tagsString == null || tagsString.trim().isEmpty()) {
            return Collections.emptyList(); // Return empty list if tags are empty or null
        }

        // Split tags by commas, trim whitespace and filter out any empty strings
        return Arrays.stream(tagsString.split(","))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .collect(Collectors.toList());
    }
}
