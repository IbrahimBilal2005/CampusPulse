package data_access;

import entity.Event;
import use_case.new_event_post.NewEventPostUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventDataAccess implements NewEventPostUserDataAccessInterface {
    private final Map<String, Event> eventStore = new HashMap<>(); // Key: Event Name, Value: Event

    @Override
    public boolean existsByName(String eventName) {
        // Check if an event with the given name exists
        return eventStore.containsKey(eventName);
    }

    @Override
    public void addEvent(Event event) {
        // Add the event to the in-memory store
        eventStore.put(event.getName(), event);
    }
}
