package use_case.sort;

import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortInteractorTest {

    private SortOutputBoundary sortOutputBoundary;
    private SortInteractor sortInteractor;

    @BeforeEach
    void setUp() {
        sortOutputBoundary = new SortOutputBoundary() {
            @Override
            public void prepareSuccessView(SortOutputData outputData) {
                // We will use assertions inside the mock method, in place of verify.
                sortedEvents = outputData.getSortedEvents();
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);  // Ensure failure causes the test to fail
            }
        };
        sortInteractor = new SortInteractor(sortOutputBoundary);
    }

    private List<Event> sortedEvents; // to store results for assertions

    @Test
    void testSortWithNoEvents() {
        SortInputData inputData = new SortInputData("Earliest", Arrays.asList());
        sortInteractor.sort(inputData);

        // Assert failure message is passed
        assertEquals(List.of(), sortedEvents);
    }

    @Test
    void testSortWithEmptySortQuery() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 11, 0, 0), Arrays.asList("tag1"));
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("", events);

        sortInteractor.sort(inputData);

        // Assert the events are returned unsorted
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event1, sortedEvents.get(0));
        assertEquals(event2, sortedEvents.get(1));
        assertEquals(sortedEvents, events);
    }

    @Test
    void testSortWithInvalidSortQuery() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 11, 0, 0), Arrays.asList("tag1"));
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("InvalidQuery", events);

        sortInteractor.sort(inputData);

        // Assert the events are returned unsorted as the query is invalid
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event1, sortedEvents.get(0));
        assertEquals(event2, sortedEvents.get(1));
        assertEquals(sortedEvents, events);
    }

    @Test
    void testSortEarliest() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 11, 0, 0), Arrays.asList("tag1"));
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("Earliest", events);

        sortInteractor.sort(inputData);

        // Assert the earliest event is first
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event2, sortedEvents.get(0)); // Earliest
        assertEquals(event1, sortedEvents.get(1)); // Latest
    }

    @Test
    void testSortLatest() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 11, 0, 0), Arrays.asList("tag1"));
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("Latest", events);

        sortInteractor.sort(inputData);

        // Assert the latest event is first
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event1, sortedEvents.get(0)); // Latest
        assertEquals(event2, sortedEvents.get(1)); // Earliest
    }

    @Test
    void testSortLongest() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 12, 0, 0), Arrays.asList("tag1")); // 2 hours
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));  // 1 hour
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("Longest", events);

        sortInteractor.sort(inputData);

        // Assert the longest event is first
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event1, sortedEvents.get(0)); // Longest
        assertEquals(event2, sortedEvents.get(1)); // Shortest
    }

    @Test
    void testSortShortest() {
        Event event1 = new Event("Event 1", "Description 1", "Location 1",
                LocalDateTime.of(2024, 12, 3, 10, 0, 0), LocalDateTime.of(2024, 12, 3, 12, 0, 0), Arrays.asList("tag1")); // 2 hours
        Event event2 = new Event("Event 2", "Description 2", "Location 2",
                LocalDateTime.of(2024, 12, 3, 9, 0, 0), LocalDateTime.of(2024, 12, 3, 10, 0, 0), Arrays.asList("tag2"));  // 1 hour
        List<Event> events = Arrays.asList(event1, event2);
        SortInputData inputData = new SortInputData("Shortest", events);

        sortInteractor.sort(inputData);

        // Assert the shortest event is first
        assertNotNull(sortedEvents);
        assertEquals(2, sortedEvents.size());
        assertEquals(event2, sortedEvents.get(0)); // Shortest
        assertEquals(event1, sortedEvents.get(1)); // Longest
    }

    @Test
    void failureNoEventsTest() {
        List<Event> events = List.of();
        SortInputData inputData = new SortInputData("", events);

        SortOutputBoundary failurePresenter = new SortOutputBoundary() {
            @Override
            public void prepareSuccessView(SortOutputData outputData) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No events found", error);
            }
        };

        SortInputBoundary interactor = new SortInteractor(failurePresenter);
        interactor.sort(inputData);
    }
}
