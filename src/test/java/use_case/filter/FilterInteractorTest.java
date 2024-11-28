package use_case.filter;

import data_access.EventDAO;
import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterInteractorTest {

    static class InMemoryFilterDataAccess implements FilterDataAccessInterface {
        private final List<Event> events;

        public InMemoryFilterDataAccess(List<Event> events) {
            this.events = events;
        }

        @Override
        public List<Event> filterEvents(Map<String, Object> criteria) {
            List<Event> filteredEvents = new ArrayList<>();
            for (Event event : events) {
                boolean matches = (criteria.get("startTime") == null || event.getStart().equals(criteria.get("startTime")))
                        && (criteria.get("location") == null || event.getLocation().equals(criteria.get("location")))
                        && (criteria.get("tags") == null || event.getTags().containsAll((List<String>) criteria.get("tags")));
                if (matches) filteredEvents.add(event);
            }
            return filteredEvents;
        }

        @Override
        public List<Event> getAllEvents() {
            return events;
        }
    }

    @Test
    void successTest() {
        // In-memory event repository with predefined events

        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate output
        FilterOutputBoundary successPresenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                // Validate the output data
                assertNotNull(outputData.getEvents());
                assertEquals(1, outputData.getEvents().size());
                assertEquals("Mat137", outputData.getEvents().get(0).getName());
            }

            @Override
            public void setFailView(String message) {
                fail("Use case failure is unexpected in successTest.");
            }
        };

        // Input data for filtering
        FilterInputData inputData = new FilterInputData(1, "Toronto", List.of("tech"));

        // Create and execute the interactor
        FilterInteractor interactor = new FilterInteractor(dataAccess, successPresenter);
        interactor.filter(inputData);
    }

    @Test
    void failureTest() {
        // In-memory event repository with predefined events
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate failure
        FilterOutputBoundary failurePresenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                fail("Use case success is unexpected in failureTest.");
            }

            @Override
            public void setFailView(String message) {
                // Validate the failure message
                assertEquals("No events found matching the filter criteria.", message);
            }
        };

        // Input data for filtering that won't match any events
        FilterInputData inputData = new FilterInputData(null, "Vancouver", List.of("sports"));

        // Create and execute the interactor
        FilterInteractor interactor = new FilterInteractor(dataAccess, failurePresenter);
        interactor.filter(inputData);
    }

    @Test
    void filterByLocation() {
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate output
        FilterOutputBoundary presenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                assertNotNull(outputData.getEvents());
                assertEquals(1, outputData.getEvents().size());
                assertEquals("Yoga for Beginners", outputData.getEvents().get(0).getName());
            }

            @Override
            public void setFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };

        // Filter by location "Wellness Center, Yoga Studio"
        FilterInputData inputData = new FilterInputData(null, "Wellness Center, Yoga Studio", null);

        FilterInteractor interactor = new FilterInteractor(dataAccess, presenter);
        interactor.filter(inputData);
    }

    @Test
    void filterByTags() {
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate output
        FilterOutputBoundary presenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                assertNotNull(outputData.getEvents());
                assertEquals(2, outputData.getEvents().size());
                // Check if the tags filter works (AI or Technology)
                assertTrue(outputData.getEvents().stream().anyMatch(event -> event.getName().equals("Tech Talk: AI in Healthcare")));
                assertTrue(outputData.getEvents().stream().anyMatch(event -> event.getName().equals("AI for Beginners Workshop")));
            }

            @Override
            public void setFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };

        // Filter by tags ["AI", "Technology"]
        FilterInputData inputData = new FilterInputData(null, null, Arrays.asList("AI", "Technology"));

        FilterInteractor interactor = new FilterInteractor(dataAccess, presenter);
        interactor.filter(inputData);
    }

    @Test
    void filterByStartTime() {
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate output
        FilterOutputBoundary presenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                assertNotNull(outputData.getEvents());
                assertEquals(2, outputData.getEvents().size());
                assertEquals("Yoga for Beginners", outputData.getEvents().get(0).getName());
                assertEquals("Mat137", outputData.getEvents().get(1).getName());
            }

            @Override
            public void setFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };

        // Filter by a specific start time (e.g., the current time)
        LocalDateTime now = LocalDateTime.now();
        FilterInputData inputData = new FilterInputData(1, "", null);

        FilterInteractor interactor = new FilterInteractor(dataAccess, presenter);
        interactor.filter(inputData);
    }

    @Test
    void filterByDuration() {
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate output
        FilterOutputBoundary presenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                assertNotNull(outputData.getEvents());
                assertEquals(5, outputData.getEvents().size());
                // Assuming the event duration for "Mat137" is 2 hours
                assertEquals("Tech Talk: AI in Healthcare", outputData.getEvents().get(0).getName());
            }

            @Override
            public void setFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };

        // Filter by duration (e.g., 2 hours)
        FilterInputData inputData = new FilterInputData(2, null, null);

        FilterInteractor interactor = new FilterInteractor(dataAccess, presenter);
        interactor.filter(inputData);
    }

    @Test
    void filterByTagsNoMatch() {
        EventDAO dataAccess = new EventDAO();

        // Define a custom presenter to validate failure
        FilterOutputBoundary failurePresenter = new FilterOutputBoundary() {
            @Override
            public void setPassView(FilterOutputData outputData) {
                fail("Use case success is unexpected in filterByTagsNoMatch.");
            }

            @Override
            public void setFailView(String message) {
                // Validate the failure message
                assertEquals("No events found matching the filter criteria.", message);
            }
        };

        // Filter by tags that don't match any events
        FilterInputData inputData = new FilterInputData(null, null, Arrays.asList("Nonexistent", "Tag"));

        FilterInteractor interactor = new FilterInteractor(dataAccess, failurePresenter);
        interactor.filter(inputData);
    }
}
