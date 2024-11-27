package use_case.search;

import entity.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SearchOutputDataTest {

    @Test
    public void testConstructorAndGetter() {
        // Test constructor and getter for events
        List<Event> events = Arrays.asList(
                new Event(1, "Hackathon", "A tech competition", "Tech Center", LocalDateTime.now(), LocalDateTime.now().plusHours(3), Arrays.asList("Tech", "Competition")),
                new Event(2, "Sports Day", "Outdoor sports event", "Sports Field", LocalDateTime.now(), LocalDateTime.now().plusHours(4), Arrays.asList("Sports", "Outdoor"))
        );
        SearchOutputData outputData = new SearchOutputData(events);

        assertNotNull(outputData, "Output data should not be null.");
        assertEquals(events, outputData.getEvents(), "Events should match the input.");
    }

    @Test
    public void testEmptyEventsList() {
        // Test case where no events are found
        List<Event> emptyEvents = Arrays.asList();
        SearchOutputData outputData = new SearchOutputData(emptyEvents);

        assertNotNull(outputData, "Output data should not be null.");
        assertTrue(outputData.getEvents().isEmpty(), "Event list should be empty.");
    }

    @Test
    public void testSingleEvent() {
        // Test case with a single event
        Event event = new Event(1, "Concert", "Music concert", "City Hall", LocalDateTime.now(), LocalDateTime.now().plusHours(5), Arrays.asList("Music", "Entertainment"));
        List<Event> singleEventList = Arrays.asList(event);
        SearchOutputData outputData = new SearchOutputData(singleEventList);

        assertNotNull(outputData, "Output data should not be null.");
        assertEquals(1, outputData.getEvents().size(), "There should be exactly one event.");
        assertTrue(outputData.getEvents().contains(event), "Event list should contain the specified event.");
    }
}
