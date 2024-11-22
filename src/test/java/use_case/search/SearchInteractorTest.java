package use_case.search;

import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    @Test
    void testSearchByCategory_ExistingCategory() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A", LocalDateTime.now(), LocalDateTime.now().plusHours(2), Arrays.asList("fun", "outdoor"));

        // Custom implementation for SearchDataAccessInterface
        SearchDataAccessInterface searchDAO = new SearchDataAccessInterface() {
            @Override
            public List<Event> searchEvents(String category, List<String> tags) {
                if (category.equals("Music")) {
                    return Arrays.asList(event1);
                }
                return Arrays.asList();
            }

            @Override
            public List<Event> getAllEvents() {
                return Arrays.asList(event1);
            }
        };

        // Custom implementation for SearchOutputBoundary
        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            private String errorMessage;
            private SearchOutputData outputData;

            @Override
            public void setFailView(String error) {
                this.errorMessage = error;
            }

            @Override
            public void setPassView(SearchOutputData outputData) {
                this.outputData = outputData;
            }

            @Override
            public SearchOutputData getOutputData() {
                return outputData;  // Return the stored output data
            }

            public String getErrorMessage() {
                return errorMessage;
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchDAO, outputBoundary);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("fun"));

        // Act
        interactor.search(inputData);

        // Assert
        assertNull(outputBoundary.getErrorMessage()); // No error message should be set
        assertNotNull(outputBoundary.getOutputData()); // Output data should be set
        assertEquals(1, outputBoundary.getOutputData().getEvents().size()); // Should return the correct number of events
        assertEquals("Music", outputBoundary.getOutputData().getEvents().get(0).getName()); // Event should match the expected
    }

    @Test
    void testSearchByCategory_NoMatchingEvents() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A", LocalDateTime.now(), LocalDateTime.now().plusHours(2), Arrays.asList("fun", "outdoor"));

        SearchDataAccessInterface searchDAO = new SearchDataAccessInterface() {
            @Override
            public List<Event> searchEvents(String category, List<String> tags) {
                return Arrays.asList();  // No events matching
            }

            @Override
            public List<Event> getAllEvents() {
                return Arrays.asList(event1);
            }
        };

        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            private String errorMessage;
            private SearchOutputData outputData;

            @Override
            public void setFailView(String error) {
                this.errorMessage = error;
            }

            @Override
            public void setPassView(SearchOutputData outputData) {
                this.outputData = outputData;
            }

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }

            public SearchOutputData getOutputData() {
                return outputData;
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchDAO, outputBoundary);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("indoor"));

        // Act
        interactor.search(inputData);

        // Assert
        assertEquals("No events found", outputBoundary.getErrorMessage()); // Check error message
    }

    @Test
    void testSearchByCategoryAndTags() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                Arrays.asList("fun", "outdoor"));

        Event event2 = new Event(2, "Music", "Another music event", "Location B",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2),
                Arrays.asList("party", "indoor"));

        SearchDataAccessInterface searchDAO = new SearchDataAccessInterface() {
            @Override
            public List<Event> searchEvents(String category, List<String> categories) { // Updated parameter name
                if ("Music".equals(category) && categories.contains("party")) {
                    return Arrays.asList(event2);
                }
                return Arrays.asList();
            }

            @Override
            public List<Event> getAllEvents() {
                return Arrays.asList(event1, event2);
            }
        };

        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            private String errorMessage;
            private SearchOutputData outputData;

            @Override
            public void setFailView(String error) {
                this.errorMessage = error;
            }

            @Override
            public void setPassView(SearchOutputData outputData) {
                this.outputData = outputData;
            }

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }

            public SearchOutputData getOutputData() {
                return outputData;
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchDAO, outputBoundary);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("party"));

        // Act
        interactor.search(inputData);

        // Assert
        assertNotNull(outputBoundary.getOutputData());
        assertEquals(1, outputBoundary.getOutputData().getEvents().size());  // Only 1 matching event
        assertEquals("Music", outputBoundary.getOutputData().getEvents().get(0).getName());  // Check event name
    }


    @Test
    void testSearchWithMultipleEvents() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A", LocalDateTime.now(), LocalDateTime.now().plusHours(2), Arrays.asList("fun", "outdoor"));
        Event event2 = new Event(2, "Music", "Another music event", "Location B", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), Arrays.asList("fun", "indoor"));
        Event event3 = new Event(3, "Sports", "Football match", "Location C", LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4), Arrays.asList("outdoor", "sports"));

        SearchDataAccessInterface searchDAO = new SearchDataAccessInterface() {
            @Override
            public List<Event> searchEvents(String category, List<String> tags) {
                if ("Music".equals(category)) {
                    return Arrays.asList(event1, event2);
                }
                return Arrays.asList();
            }

            @Override
            public List<Event> getAllEvents() {
                return Arrays.asList(event1, event2, event3);
            }
        };

        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            private String errorMessage;
            private SearchOutputData outputData;

            @Override
            public void setFailView(String error) {
                this.errorMessage = error;
            }

            @Override
            public void setPassView(SearchOutputData outputData) {
                this.outputData = outputData;
            }

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }

            public SearchOutputData getOutputData() {
                return outputData;
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchDAO, outputBoundary);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("fun"));

        // Act
        interactor.search(inputData);

        // Assert
        assertNotNull(outputBoundary.getOutputData());
        assertEquals(2, outputBoundary.getOutputData().getEvents().size());  // 2 events matching
        assertEquals("Music", outputBoundary.getOutputData().getEvents().get(0).getName());  // Check first event
        assertEquals("Music", outputBoundary.getOutputData().getEvents().get(1).getName());  // Check second event
    }

    @Test
    void testSearchWithEmptyInput() {
        // Arrange
        SearchDataAccessInterface searchDAO = new SearchDataAccessInterface() {
            @Override
            public List<Event> searchEvents(String category, List<String> tags) {
                return Arrays.asList();  // No events found
            }

            @Override
            public List<Event> getAllEvents() {
                return Arrays.asList();
            }
        };

        SearchOutputBoundary outputBoundary = new SearchOutputBoundary() {
            private String errorMessage;
            private SearchOutputData outputData;

            @Override
            public void setFailView(String error) {
                this.errorMessage = error;
            }

            @Override
            public void setPassView(SearchOutputData outputData) {
                this.outputData = outputData;
            }

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }

            public SearchOutputData getOutputData() {
                return outputData;
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchDAO, outputBoundary);
        SearchInputData inputData = new SearchInputData("", Arrays.asList());  // Empty input

        // Act
        interactor.search(inputData);

        // Assert
        assertEquals("No events found", outputBoundary.getErrorMessage()); // Check error message
    }
}
