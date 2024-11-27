package use_case.search;

import data_access.EventDAO;
import entity.Event;
import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    @Test
    void searchTestWithMatchingQuery() {
        // Initialize EventDAO with events that may contain "AI" in the name or tags
        EventDAO eventDAO = new EventDAO();

        // Create the SearchInputData object with the query "AI"
        SearchInputData inputData = new SearchInputData("AI");

        // Define a successPresenter to verify correct behavior
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that the result contains at least one event with the keyword "AI"
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'AI'.");

                // Check that at least one event contains "AI" in its name or tags
                boolean foundMatch = events.stream().anyMatch(event -> {
                    String eventName = event.getName().toLowerCase();
                    boolean containsAIInName = eventName.contains("ai");

                    boolean containsAIInTags = event.getTags().stream()
                            .anyMatch(tag -> tag.toLowerCase().contains("ai"));

                    // Return true if either name or tags contain "AI"
                    return containsAIInName || containsAIInTags;
                });

                // Assert that at least one event matches the query
                assertTrue(foundMatch, "No events found matching the search term 'AI' in name or tags.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);

        // The successPresenter will be triggered, and assertions will be checked in setPassView
    }

    @Test
    void searchTestWithNoMatchingQuery() {
        // Initialize EventDAO with sample events
        EventDAO eventDAO = new EventDAO();

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
        // Initialize EventDAO with sample events
        EventDAO eventDAO = new EventDAO();

        // Create the SearchInputData object with a query that partially matches events ("Tech")
        SearchInputData inputData = new SearchInputData("Music");

        // Define a successPresenter to check correct behavior
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that events are returned containing the keyword "Tech"
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'Music'.");

                // Check that at least one event contains "Tech" in its name or tags
                boolean foundMatch = events.stream().anyMatch(event -> {
                    String eventName = event.getName().toLowerCase();
                    boolean containsTechInName = eventName.contains("music");

                    boolean containsTechInTags = event.getTags().stream()
                            .anyMatch(tag -> tag.toLowerCase().contains("music"));

                    // Return true if either name or tags contain "Tech"
                    return containsTechInName || containsTechInTags;
                });

                // Assert that at least one event matches the query
                assertTrue(foundMatch, "No events found matching the search term 'Music' in name or tags.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);

        // The successPresenter will be triggered, and assertions will be checked in setPassView
    }

    @Test
    void searchTestWithEmptyQuery() {
        // Initialize EventDAO with sample events
        EventDAO eventDAO = new EventDAO();

        // Create the SearchInputData object with an empty query
        SearchInputData inputData = new SearchInputData("");

        // Define a successPresenter to check behavior when the query is empty
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that all events are returned for an empty search query
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for an empty search query.");
                assertEquals(6, events.size(), "The number of events should be 6.");
            }

            @Override
            public void setFailView(String error) {
                fail("Unexpected failure: " + error);
            }
        };

        // Instantiate the SearchInteractor and execute the search
        SearchInteractor interactor = new SearchInteractor(eventDAO, successPresenter);
        interactor.search(inputData);

        // The successPresenter will be triggered, and assertions will be checked in prepareSuccessView
    }

    @Test
    void testGetAllEvents() {
        // Initialize EventDAO with sample events
        EventDAO eventDAO = new EventDAO();

        // Retrieve all events from the EventDAO
        List<Event> events = eventDAO.getAllEvents();

        // Assert that the correct number of events are returned
        assertEquals(6, events.size(), "There should be 6 events in the DAO.");

        // Assert that the event names match the expected sample event names
        assertEquals("Tech Talk: AI in Healthcare", events.get(0).getName());
        assertEquals("Football Tournament Finals", events.get(1).getName());
        assertEquals("Startup Pitch Night", events.get(2).getName());
        assertEquals("Yoga for Beginners", events.get(3).getName());
        assertEquals("AI for Beginners Workshop", events.get(4).getName());
        assertEquals("Music Jam Session", events.get(5).getName());
    }

    @Test
    void searchTestWithTagMatch() {
        // Initialize EventDAO with sample events
        EventDAO eventDAO = new EventDAO();

        // Create the SearchInputData object with a query that matches a tag ("Yoga")
        SearchInputData inputData = new SearchInputData("Yoga");

        // Define a successPresenter to verify correct behavior for tag matching
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void setPassView(SearchOutputData outputData) {
                // Assert that the result contains events with the "Yoga" tag
                List<Event> events = outputData.getEvents();
                assertFalse(events.isEmpty(), "No events found for the search term 'Yoga'.");
                events.forEach(event -> {
                    assertTrue(event.getTags().contains("Yoga"),
                            "Event does not contain the tag 'Yoga'.");
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

        // The successPresenter will be triggered, and assertions will be checked in prepareSuccessView
    }
}
