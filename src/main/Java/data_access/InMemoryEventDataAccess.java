package data_access;

import entity.Event;
import entity.EventPoster;
import use_case.new_event_post.NewEventPostUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventDataAccess implements NewEventPostUserDataAccessInterface {
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
        // Check all EventPosters' events to see if the name exists
        for (EventPoster eventPoster : eventPosterStore.values()) {
            if (eventPoster.getEvents().containsKey(eventName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addtoMyevents(Event event, String username) {
        // Retrieve the EventPoster by username
        EventPoster eventPoster = eventPosterStore.get(username);

        eventPoster.getEvents().put(event.getName(), event);
        }
}
