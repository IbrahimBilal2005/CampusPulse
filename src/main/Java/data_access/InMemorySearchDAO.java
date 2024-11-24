package data_access;

import entity.Event;
import use_case.search.SearchDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemorySearchDAO implements SearchDataAccessInterface {

    private final List<Event> events;

    public InMemorySearchDAO(List<Event> events) {
        this.events = events;
    }

    @Override
    public List<Event> searchEvents(String category, List<String> tags) {
        return events.stream()
                .filter(event -> tags.stream().anyMatch(event.getTags()::contains)) // Match tags
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events); // Return all events
    }
}
