package use_case.search;

import data_access.EventDAO;
import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {
    private EventDAO eventDAO;

    @BeforeEach
    public void setUp() {
        // Initialize EventDAO with test data for each test
        List<Event> testEvents = Arrays.asList(
                new Event("Test Event 1", "Description 1", "Location 1",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                        Arrays.asList("Test", "Search")),
                new Event("Test Event 2", "Description 2", "Location 2",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(3),
                        Arrays.asList("Filter", "Search")),
                new Event("Test Event 3", "Description 3", "Location 3",
                        LocalDateTime.now(), LocalDateTime.now().plusHours(4),
                        Arrays.asList("Other", "Category"))
        );

        // Create the EventDAO and load the test events
        eventDAO = new EventDAO();
        eventDAO.setEvents(testEvents);
    }

    @Test
    void searchTestWithMatchingQuery() {
        // Create the SearchInputData object with the query "Search"
        SearchInputData inputData = new SearchInputData("Search");

        // Define a successPresenter to verify correct behavior
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that the result contains at least one event with the keyword "Search"
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'Search'.");

                // Check that at least one event contains "Search" in its name or tags
                boolean foundMatch = events.stream().anyMatch(event -> {
                    String eventName = event.getName().toLowerCase();
                    boolean containsSearchInName = eventName.contains("search");

                    boolean containsSearchInTags = event.getTags().stream()
                            .anyMatch(tag -> tag.toLowerCase().contains("search"));

                    // Return true if either name or tags contain "Search"
                    return containsSearchInName || containsSearchInTags;
                });

                // Assert that at least one event matches the query
                assertTrue(foundMatch, "No events found matching the search term 'Search' in name or tags.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void searchTestWithNoMatchingQuery() {
        // Create the SearchInputData object with a query that doesn't match any events
        SearchInputData inputData = new SearchInputData("NonexistentEvent");

        // Define a successPresenter for checking the behavior on no matching events
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                fail("Unexpected success: There should be no matching events.");
            }

            @Override
            public void setFailView(String error) {
                // Assert that the error message corresponds to the expected failure
                assertEquals("No events found matching your search query.", error, "The error message should match the expected failure message.");
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void searchTestWithPartialMatchQuery() {
        // Create the SearchInputData object with a query that partially matches events ("Event")
        SearchInputData inputData = new SearchInputData("Event");

        // Define a successPresenter to check correct behavior
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that events are returned containing the keyword "Event"
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'Event'.");

                // Check that at least one event contains "Event" in its name or tags
                boolean foundMatch = events.stream().anyMatch(event -> {
                    String eventName = event.getName().toLowerCase();
                    boolean containsEventInName = eventName.contains("event");

                    boolean containsEventInTags = event.getTags().stream()
                            .anyMatch(tag -> tag.toLowerCase().contains("event"));

                    // Return true if either name or tags contain "Event"
                    return containsEventInName || containsEventInTags;
                });

                // Assert that at least one event matches the query
                assertTrue(foundMatch, "No events found matching the search term 'Event' in name or tags.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void searchTestWithEmptyQuery() {
        // Create the SearchInputData object with an empty query
        SearchInputData inputData = new SearchInputData("");

        // Define a successPresenter to check behavior when the query is empty
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that all events are returned for an empty search query
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for an empty search query.");
                assertEquals(3, events.size(), "The number of events should match the test data.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void testGetAllEvents() {
        // Retrieve all events from the EventDAO
        List<Event> events = eventDAO.getAllEvents();

        // Assert that the correct number of events are returned
        assertEquals(3, events.size(), "There should be 3 events in the DAO.");

        // Assert that the event names match the expected sample event names
        assertEquals("Test Event 1", events.get(0).getName());
        assertEquals("Test Event 2", events.get(1).getName());
        assertEquals("Test Event 3", events.get(2).getName());
    }

    @Test
    void searchTestWithTagMatch() {
        // Create the SearchInputData object with a query that matches a tag ("Search")
        SearchInputData inputData = new SearchInputData("Search");

        // Define a successPresenter to verify correct behavior for tag matching
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that the result contains events with the "Search" tag
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'Search'.");
                events.forEach(event -> {
                    assertTrue(event.getTags().contains("Search"),
                            "Event does not contain the tag 'Search'.");
                });
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);
    }

    @Test
    void searchTestWithNoEventsAvailable() {
        // Initialize SearchDataAccessInterface with no events
        SearchDataAccessInterface dataAccess = new SearchDataAccessInterface() {
            @Override
            public List<Event> getAllEvents() {
                return List.of();  // Return an empty list to simulate no events
            }

            @Override
            public List<Event> searchEvents(String query) {
                return List.of();  // Return an empty list for any query
            }
        };

        // Create the SearchInputData object with an empty query (no search terms)
        SearchInputData inputData = new SearchInputData("");

        // Define a successPresenter for checking behavior when no events are available
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                fail("Unexpected success: There should be no events available.");
            }

            @Override
            public void setFailView(String error) {
                // Assert that the error message corresponds to the expected failure
                assertEquals("No events available.", error, "The error message should match the expected message.");
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(dataAccess, successPresenter);
        interactor.search(inputData);
    }

}
