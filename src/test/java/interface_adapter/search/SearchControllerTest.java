package interface_adapter.search;

import org.junit.jupiter.api.Test;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import static org.junit.jupiter.api.Assertions.*;

class SearchControllerTest {

    static class MockSearchInputBoundary implements SearchInputBoundary {
        private SearchInputData receivedInput;

        @Override
        public void search(SearchInputData inputData) {
            this.receivedInput = inputData;
        }

        public SearchInputData getReceivedInput() {
            return receivedInput;
        }
    }

    @Test
    void testSearchController() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input
        String keyword = "Sports";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertEquals("Sports", receivedInput.getQuery(), "Query should match the keyword.");
    }

    @Test
    void testSearchControllerNoFilters() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with no filters
        String keyword = "Music";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertEquals("Music", receivedInput.getQuery(), "Query should match the keyword.");
    }

    @Test
    void testSearchControllerNullFilters() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with null filters
        String keyword = "Workshops";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertEquals("Workshops", receivedInput.getQuery(), "Query should match the keyword.");
    }

    @Test
    void testSearchControllerEmptyKeyword() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with an empty keyword
        String keyword = "";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertEquals("", receivedInput.getQuery(), "Query should be empty.");
    }

    @Test
    void testSearchControllerNullKeyword() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with a null keyword
        String keyword = null;

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertNull(receivedInput.getQuery(), "Query should be null.");
    }

    @Test
    void testSearchControllerComplexFilters() {
        // Create a mock interactor
        MockSearchInputBoundary mockInteractor = new MockSearchInputBoundary();

        // Create the controller
        SearchController controller = new SearchController(mockInteractor);

        // Define input with complex filters
        String keyword = "Technology";

        // Invoke the method
        controller.search(keyword);

        // Verify behavior
        SearchInputData receivedInput = mockInteractor.getReceivedInput();
        assertNotNull(receivedInput, "Input data should not be null.");
        assertEquals("Technology", receivedInput.getQuery(), "Query should match the keyword.");
    }
}
