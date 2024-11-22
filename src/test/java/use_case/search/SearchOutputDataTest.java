package use_case.search;

import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchOutputDataTest {

    @Test
    void testValidOutputDataConstruction() {
        Event event = new Event(
                1,
                "Book Reading Club",
                "A gathering for book enthusiasts.",
                "Library Hall A",
                LocalDateTime.of(2024, 11, 18, 18, 0),
                LocalDateTime.of(2024, 11, 18, 20, 0),
                List.of("reading", "books")
        );
        SearchOutputData outputData = new SearchOutputData(List.of(event));

        assertEquals(1, outputData.getEvents().size());
        assertEquals("Book Reading Club", outputData.getEvents().get(0).getName());
        assertEquals("Library Hall A", outputData.getEvents().get(0).getLocation());
        assertEquals(List.of("reading", "books"), outputData.getEvents().get(0).getTags());
    }

    @Test
    void testEmptyOutputData() {
        SearchOutputData outputData = new SearchOutputData(new ArrayList<>());

        assertTrue(outputData.getEvents().isEmpty());
    }

    @Test
    void testMultipleEventsOutput() {
        Event event1 = new Event(
                1,
                "Book Reading Club",
                "A gathering for book enthusiasts.",
                "Library Hall A",
                LocalDateTime.of(2024, 11, 18, 18, 0),
                LocalDateTime.of(2024, 11, 18, 20, 0),
                List.of("reading", "books")
        );
        Event event2 = new Event(
                2,
                "Poetry Night",
                "An evening of poetic expressions.",
                "Auditorium B",
                LocalDateTime.of(2024, 11, 20, 19, 0),
                LocalDateTime.of(2024, 11, 20, 21, 0),
                List.of("poetry", "writing")
        );
        SearchOutputData outputData = new SearchOutputData(List.of(event1, event2));

        assertEquals(2, outputData.getEvents().size());
        assertEquals("Poetry Night", outputData.getEvents().get(1).getName());
        assertEquals("Auditorium B", outputData.getEvents().get(1).getLocation());
    }
}
