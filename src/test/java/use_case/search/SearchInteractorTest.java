package use_case.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import entity.Event;
import use_case.search.*;
import data_access.InMemorySearchDAO;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;

public class SearchInteractorTest {

    // Helper method to create a common set of events
    private List<Event> createSampleEvents() {
        return Arrays.asList(
                new Event(1, "Hackathon", "A tech competition", "Tech Center", LocalDateTime.now(), LocalDateTime.now().plusHours(3), Arrays.asList("Tech", "Competition")),
                new Event(2, "Sports Day", "Outdoor sports event", "Sports Field", LocalDateTime.now(), LocalDateTime.now().plusHours(4), Arrays.asList("Sports", "Outdoor")),
                new Event(3, "Concert", "Music concert", "City Hall", LocalDateTime.now(), LocalDateTime.now().plusHours(5), Arrays.asList("Music", "Entertainment")),
                new Event(4, "Workshop", "Programming workshop", "Tech Hub", LocalDateTime.now(), LocalDateTime.now().plusHours(2), Arrays.asList("Tech", "Workshop")),
                new Event(5, "Networking Event", "Professional networking", "Convention Center", LocalDateTime.now(), LocalDateTime.now().plusHours(3), Arrays.asList("Networking", "Career"))
        );
    }

    @Test
    public void emptyQueryTest_shouldReturnAllEvents() {
        List<Event> events = createSampleEvents();  // Get common events

        // In-memory data access object (DAO) with mock events
        InMemorySearchDAO dataAccess = new InMemorySearchDAO(events);

        // Create a mock Presenter
        SearchViewModel viewModel = new SearchViewModel("Search");
        new SearchPresenter(viewModel) {
            @Override
            public void setPassView(SearchOutputData outputData) {
                super.setPassView(outputData);
                // Assert that all events are returned
                List<Event> returnedEvents = outputData.getEvents();
                assertEquals(5, returnedEvents.size());  // We expect all 5 events
                events.forEach(event -> assertTrue(returnedEvents.contains(event)));  // Ensure each event is returned
            }

            @Override
            public void setFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // Create the Interactor
        SearchInputBoundary interactor = new SearchInteractor(dataAccess);

        // Search with an empty query
        SearchInputData inputData = new SearchInputData("");
        interactor.search(inputData);  // This should return all events
    }
}
