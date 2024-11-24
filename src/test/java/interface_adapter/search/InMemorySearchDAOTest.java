package interface_adapter.search;

import entity.Event;
import org.junit.jupiter.api.Test;
import data_access.InMemorySearchDAO;
import use_case.search.SearchDataAccessInterface;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemorySearchDAOTest {

    @Test
    void testSearchEventsByTags() {
        // Sample data
        List<Event> events = List.of(
                new Event(1, "Concert Night", "A fun live music event", "Concert Hall",
                        LocalDateTime.of(2024, 11, 30, 19, 0),
                        LocalDateTime.of(2024, 11, 30, 22, 0),
                        List.of("music", "live")),
                new Event(2, "Sports Day", "Outdoor sports competition", "Sports Arena",
                        LocalDateTime.of(2024, 12, 1, 9, 0),
                        LocalDateTime.of(2024, 12, 1, 17, 0),
                        List.of("outdoor", "sports")),
                new Event(3, "Art Workshop", "Learn to paint and sculpt", "Art Studio",
                        LocalDateTime.of(2024, 12, 5, 10, 0),
                        LocalDateTime.of(2024, 12, 5, 12, 0),
                        List.of("art", "painting"))
        );

        // DAO Implementation
        SearchDataAccessInterface dao = new InMemorySearchDAO(events);

        // Search for events tagged with "music"
        List<Event> musicEvents = dao.searchEvents(null, List.of("music"));

        // Assertions
        assertEquals(1, musicEvents.size());
        assertEquals("Concert Night", musicEvents.get(0).getName());

        // Search for events tagged with "outdoor"
        List<Event> outdoorEvents = dao.searchEvents(null, List.of("outdoor"));

        // Assertions
        assertEquals(1, outdoorEvents.size());
        assertEquals("Sports Day", outdoorEvents.get(0).getName());

        // Search for events tagged with "painting"
        List<Event> paintingEvents = dao.searchEvents(null, List.of("painting"));

        // Assertions
        assertEquals(1, paintingEvents.size());
        assertEquals("Art Workshop", paintingEvents.get(0).getName());
    }

    @Test
    void testSearchSportsEvents() {
        // Sample data: 5 events, 2 of them are sports-related
        List<Event> events = List.of(
                new Event(1, "Soccer Match", "Exciting soccer game", "Stadium",
                        LocalDateTime.of(2024, 12, 1, 18, 0),
                        LocalDateTime.of(2024, 12, 1, 21, 0),
                        List.of("sports", "outdoor", "soccer")),
                new Event(2, "Basketball Game", "Local basketball league", "Arena",
                        LocalDateTime.of(2024, 12, 5, 19, 0),
                        LocalDateTime.of(2024, 12, 5, 22, 0),
                        List.of("sports", "indoor", "basketball")),
                new Event(3, "Cooking Workshop", "Learn to cook gourmet meals", "Community Center",
                        LocalDateTime.of(2024, 12, 3, 10, 0),
                        LocalDateTime.of(2024, 12, 3, 13, 0),
                        List.of("cooking", "food", "workshop")),
                new Event(4, "Art Exhibition", "Showcase of local art", "Gallery",
                        LocalDateTime.of(2024, 12, 7, 15, 0),
                        LocalDateTime.of(2024, 12, 7, 18, 0),
                        List.of("art", "exhibition")),
                new Event(5, "Tech Conference", "Latest in tech and innovation", "Convention Center",
                        LocalDateTime.of(2024, 12, 9, 9, 0),
                        LocalDateTime.of(2024, 12, 9, 17, 0),
                        List.of("technology", "innovation", "conference"))
        );

        // DAO Implementation
        SearchDataAccessInterface dao = new InMemorySearchDAO(events);

        // Search for events tagged with "sports"
        List<Event> sportsEvents = dao.searchEvents(null, List.of("sports"));

        // Assertions
        assertEquals(2, sportsEvents.size(), "Only 2 events should be sports-related");
        assertEquals("Soccer Match", sportsEvents.get(0).getName());
        assertEquals("Basketball Game", sportsEvents.get(1).getName());
    }
}
