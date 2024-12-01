package use_case.delete_event;

import data_access.InMemoryUserDataAccessObject;
import entity.Event;
import entity.EventPoster;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DeleteEventInteractorTest {

    @Test
    void successTest() {
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("Education Seminar", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put("event1", event1);
        eventMap.put("event2", event2);

        EventPoster eventPoster = new EventPoster("john_doe", "password123", "TechCorp", "http://sop.link", eventMap);

        DeleteEventInputData deleteEventInputData = new DeleteEventInputData("username", event1);
        DeleteEventDataAccessInterface userRepository = new InMemoryUserDataAccessObject()
    }
}
