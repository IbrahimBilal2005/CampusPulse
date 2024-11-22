package use_case.search;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String searchQuery = "reading";
        List<String> tags = List.of("fiction", "book club");

        // Act
        SearchInputData inputData = new SearchInputData(searchQuery, tags);

        // Assert
        assertEquals("reading", inputData.getCategory());
        assertEquals(List.of("fiction", "book club"), inputData.getTags());
    }

    @Test
    void testEmptyTags() {
        // Arrange
        String searchQuery = "movies";

        // Act
        SearchInputData inputData = new SearchInputData(searchQuery, List.of());

        // Assert
        assertEquals("movies", inputData.getCategory());
        assertTrue(inputData.getTags().isEmpty());
    }

    @Test
    void testNullTags() {
        // Arrange
        String searchQuery = "coding";

        // Act
        SearchInputData inputData = new SearchInputData(searchQuery, null);

        // Assert
        assertEquals("coding", inputData.getCategory());
        assertNull(inputData.getTags());
    }
}
