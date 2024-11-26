package use_case.search;

import data_access.InMemorySearchDAO;
import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    @Test
    void successTest_SearchByCategory_ExistingCategory() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                Arrays.asList("fun", "outdoor"));

        // In-memory implementation of SearchDataAccessInterface
        SearchDataAccessInterface searchRepository = new InMemorySearchDAO(new ArrayList<>(List.of(event1)));

        // Anonymous implementation of SearchOutputBoundary for success testing
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assertions for success case
                assertNotNull(outputData);
                assertEquals(1, outputData.getEvents().size());
                assertEquals("Music", outputData.getEvents().get(0).getName());
            }

            @Override
            public void setFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchRepository, successPresenter);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("fun"));

        // Act
        interactor.search(inputData);
    }

    @Test
    void successTest_SearchByCategory_NoMatchingEvents() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                Arrays.asList("fun", "outdoor"));

        SearchDataAccessInterface searchRepository = new InMemorySearchDAO(new ArrayList<>(List.of(event1)));

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                fail("No events should have matched.");
            }

            @Override
            public void setFailView(String error) {
                // Assertions for failure case
                assertEquals("No events found", error);
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchRepository, successPresenter);
        SearchInputData inputData = new SearchInputData("Sports", Arrays.asList("indoor"));

        // Act
        interactor.search(inputData);
    }

    @Test
    void successTest_SearchByCategoryAndTags() {
        // Arrange
        Event event1 = new Event(1, "Music", "A music concert", "Location A",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                Arrays.asList("fun", "outdoor"));

        Event event2 = new Event(2, "Music", "Another music event", "Location B",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2),
                Arrays.asList("party", "indoor"));

        SearchDataAccessInterface searchRepository = new InMemorySearchDAO(new ArrayList<>(List.of(event1, event2)));

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assertions for success case
                assertNotNull(outputData);
                assertEquals(1, outputData.getEvents().size());
                assertEquals("Another music event", outputData.getEvents().get(0).getDescription());
            }

            @Override
            public void setFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchRepository, successPresenter);
        SearchInputData inputData = new SearchInputData("Music", Arrays.asList("party"));

        // Act
        interactor.search(inputData);
    }

    @Test
    void successTest_SearchWithEmptyInput() {
        // Arrange
        SearchDataAccessInterface searchRepository = new InMemorySearchDAO(new ArrayList<>());

        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                fail("No events should have been found.");
            }

            @Override
            public void setFailView(String error) {
                // Assertions for failure case
                assertEquals("No events found", error);
            }
        };

        SearchInteractor interactor = new SearchInteractor(searchRepository, successPresenter);
        SearchInputData inputData = new SearchInputData("", Arrays.asList());

        // Act
        interactor.search(inputData);
    }
}
