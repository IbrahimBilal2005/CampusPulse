package use_case.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchInputDataTest {

    @Test
    public void testConstructorAndGetter() {
        // Test constructor and getter for search query
        String query = "Tech";
        SearchInputData inputData = new SearchInputData(query);

        assertNotNull(inputData, "Input data should not be null.");
        assertEquals(query, inputData.getQuery(), "Query should match the input.");
    }

    @Test
    public void testEmptyQuery() {
        // Test empty query scenario
        String query = "";
        SearchInputData inputData = new SearchInputData(query);

        assertNotNull(inputData, "Input data should not be null.");
        assertEquals(query, inputData.getQuery(), "Query should be empty.");
    }

    @Test
    public void testNullQuery() {
        // Test null query scenario
        String query = null;
        SearchInputData inputData = new SearchInputData(query);

        assertNotNull(inputData, "Input data should not be null.");
        assertNull(inputData.getQuery(), "Query should be null.");
    }
}
