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
        SearchInputData inputData = new SearchInputData(searchQuery);

        // Assert
        assertEquals("reading", inputData.getQuery());
    }

    @Test
    void testEmptyTags() {
        // Arrange
        String searchQuery = "movies";

        // Act
        SearchInputData inputData = new SearchInputData(searchQuery);

        // Assert
        assertEquals("movies", inputData.getQuery());
    }

    @Test
    void testNullTags() {
        // Arrange
        String searchQuery = "coding";

        // Act
        SearchInputData inputData = new SearchInputData(searchQuery);

        // Assert
        assertEquals("coding", inputData.getQuery());
    }
}
