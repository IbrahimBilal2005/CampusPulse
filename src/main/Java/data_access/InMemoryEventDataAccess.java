package data_access;

import entity.Event;
import entity.EventPoster;
import use_case.new_event_post.NewEventPostUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventDataAccess implements NewEventPostUserDataAccessInterface {
    private final Map<String, Event> eventStore = new HashMap<>(); // Key: Event Name, Value: Event
    private final Map<String, EventPoster> eventPosterStore = new HashMap<>(); // Key: Username, Value: EventPoster

    public InMemoryEventDataAccess() {
        EventPoster predefinedPoster = new EventPoster(
                "abcd",
                "password123",
                "TechCorp",
                "www.soplink.com",
                new HashMap<>()
        );
        eventPosterStore.put(predefinedPoster.getUsername(), predefinedPoster);
    }

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

    @Override
    public void addtoMyevents(Event event, String username) {
        // Retrieve the EventPoster by username
        EventPoster eventPoster = eventPosterStore.get(username);

        eventPoster.getEvents().put(event.getName(), event);
        }
}
