package entity;

import java.util.HashMap;
import java.util.Map;

public class EventPoster implements EventPosterInterface {
    private final String username;
    private String password;
    private Map<String, Event> events;

    public EventPoster(String username, String password) {
        this.username = username;
        this.password = password;
        this.events = new HashMap<>();
    }

    @Override
    public void createEvent(Event event) {
        events.put(String.valueOf(event.getId()), event);
    }

    @Override
    public void deleteEvent(int eventID) {
        events.remove(String.valueOf(eventID));
    }

    @Override
    public String getName() { return this.username; }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public void changePassword(String newPassword) { this.password = newPassword; }
}
