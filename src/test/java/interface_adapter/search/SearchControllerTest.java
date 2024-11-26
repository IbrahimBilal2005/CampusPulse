package interface_adapter.search;
import interface_adapter.search.SearchController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class SearchControllerTest {
    @Test
    void testSearchController() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input
        String keyword = "Sports";
        Map<String, String> filters = new HashMap<>();
        filters.put("tags", "outdoor,fun");

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery().equals("Sports")
        ));
    }

    @Test
    void testSearchControllerNoFilters() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with no filters
        String keyword = "Music";
        Map<String, String> filters = new HashMap<>();

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery().equals("Music")
        ));
    }

    @Test
    void testSearchControllerNullFilters() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with null filters
        String keyword = "Workshops";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery().equals("Workshops")
        ));
    }

    @Test
    void testSearchControllerEmptyKeyword() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with an empty keyword
        String keyword = "";
        Map<String, String> filters = new HashMap<>();
        filters.put("tags", "coding,java");

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery().equals("")
        ));
    }

    @Test
    void testSearchControllerNullKeyword() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with a null keyword
        String keyword = null;
        Map<String, String> filters = new HashMap<>();
        filters.put("tags", "coding,java");

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery() == null
        ));
    }

    @Test
    void testSearchControllerComplexFilters() {
        // Mock the SearchInputBoundary
        SearchInputBoundary mockInteractor = Mockito.mock(SearchInputBoundary.class);

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with complex filters
        String keyword = "Technology";
        Map<String, String> filters = new HashMap<>();
        filters.put("tags", "AI,   machine learning ,  data-science");

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        verify(mockInteractor).search(argThat(input ->
                input.getQuery().equals("Technology")
        ));
    }
}
