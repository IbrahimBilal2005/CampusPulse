package interface_adapter.search;

import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.SearchOutputData;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchPresenterTest {

    private SearchViewModel searchUserViewModel;
    private SearchPresenter searchUserPresenter;

    @BeforeEach
    void setUp() {
        // Initialize the view model and presenter
        searchUserViewModel = new SearchViewModel("Search");
        searchUserPresenter = new SearchPresenter(searchUserViewModel);
    }

    @Test
    void testShowSuccessScreen() {
        // Given valid event data
        List<Event> events = List.of(
                new Event(1, "Soccer", "Soccer match", "Toronto",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                        Arrays.asList("fun", "outdoor"))
        );
        SearchOutputData validData = new SearchOutputData(events);

        // When setPassView is called
        searchUserPresenter.setPassView(validData);

        // Then check if the results are set correctly in the view model
        assertNotNull(searchUserViewModel.getState().getResults());
        assertEquals(1, searchUserViewModel.getState().getResults().size());
        assertEquals("Soccer", searchUserViewModel.getState().getResults().get(0).getName());
        assertNull(searchUserViewModel.getState().getError()); // No error should be set
    }

    @Test
    void testShowFailureScreen() {
        // Given an error message
        String errorMessage = "No events found";

        // When setFailView is called
        searchUserPresenter.setFailView(errorMessage);

        // Then check if the error is set correctly in the view model
        assertNotNull(searchUserViewModel.getState());  // Ensure the state is not null
        assertTrue(searchUserViewModel.getState().getResults().isEmpty(), "Results should be empty");  // Results should be empty
        assertEquals("No events found", searchUserViewModel.getState().getError(), "Error message should match");  // Error should match
    }

    @Test
    void testSetPassViewWithNullEvents() {
        // Given a null events list
        SearchOutputData invalidData = new SearchOutputData(null);

        // When setPassView is called
        searchUserPresenter.setPassView(invalidData);

        // Then check if the results are set to an empty list
        assertNotNull(searchUserViewModel.getState().getResults(), "Results should not be null");
        assertTrue(searchUserViewModel.getState().getResults().isEmpty(), "Results should be empty");
        assertNull(searchUserViewModel.getState().getError(), "Error should be null");
    }

    @Test
    void testSetPassViewWithEmptyEvents() {
        // Given an empty events list
        List<Event> emptyEvents = Collections.emptyList();
        SearchOutputData emptyData = new SearchOutputData(emptyEvents);

        // When setPassView is called
        searchUserPresenter.setPassView(emptyData);

        // Then check if the results are set to an empty list
        assertNotNull(searchUserViewModel.getState().getResults(), "Results should not be null");
        assertTrue(searchUserViewModel.getState().getResults().isEmpty(), "Results should be empty");
        assertNull(searchUserViewModel.getState().getError(), "Error should be null");
    }

    @Test
    void testSetPassViewWithMultipleEvents() {
        // Given multiple events
        List<Event> events = List.of(
                new Event(1, "Soccer Match", "An exciting soccer match", "Stadium",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                        Arrays.asList("sports", "outdoor")),
                new Event(2, "Basketball Game", "Local basketball tournament", "Arena",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(3),
                        Arrays.asList("sports", "indoor"))
        );
        SearchOutputData validData = new SearchOutputData(events);

        // When setPassView is called
        searchUserPresenter.setPassView(validData);

        // Then check if the results are correctly populated
        assertNotNull(searchUserViewModel.getState().getResults(), "Results should not be null");
        assertEquals(2, searchUserViewModel.getState().getResults().size(), "There should be 2 results");
        assertEquals("Soccer Match", searchUserViewModel.getState().getResults().get(0).getName(), "First event should be 'Soccer Match'");
        assertEquals("Basketball Game", searchUserViewModel.getState().getResults().get(1).getName(), "Second event should be 'Basketball Game'");
        assertNull(searchUserViewModel.getState().getError(), "Error should be null");
    }

    @Test
    void testSetFailViewWithNullError() {
        // Given a null error message
        String errorMessage = null;

        // When setFailView is called with null error
        searchUserPresenter.setFailView(errorMessage);

        // Then check if the error is null and results are empty
        assertTrue(searchUserViewModel.getState().getResults().isEmpty(), "Results should be empty");
        assertNull(searchUserViewModel.getState().getError(), "Error should be null");
    }

    @Test
    void testSetFailViewWithNoMatchingEvents() {
        // Given a search for events with a tag that doesn't exist
        String errorMessage = "No matching events found";

        // Simulate no matching events by calling setFailView
        searchUserPresenter.setFailView(errorMessage);

        // Then check if the error message is set correctly
        assertTrue(searchUserViewModel.getState().getResults().isEmpty(), "Results should be empty");
        assertEquals("No matching events found", searchUserViewModel.getState().getError(), "Error message should match");
    }

    @Test
    void testSetPassViewWithInvalidEventData() {
        // Given an event with missing or invalid data (empty name)
        List<Event> invalidEvents = List.of(
                new Event(1, "", "An invalid event with no name", "Unknown Location",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                        Arrays.asList("invalid", "test"))
        );
        SearchOutputData invalidData = new SearchOutputData(invalidEvents);

        // When setPassView is called with invalid data
        searchUserPresenter.setPassView(invalidData);

        // Then check if the results are still populated correctly (even if invalid)
        assertNotNull(searchUserViewModel.getState().getResults(), "Results should not be null");
        assertEquals(1, searchUserViewModel.getState().getResults().size(), "There should be 1 result");
        assertEquals("", searchUserViewModel.getState().getResults().get(0).getName(), "Event name should be empty");
        assertNull(searchUserViewModel.getState().getError(), "Error should be null");
    }

}

