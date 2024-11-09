package entity;
import entity.Interfaces.AdministratorInterface;

import java.util.Map;

public class Administrator implements AdministratorInterface {
    private String username;
    private String password;
    private Map<String, Event> events;

    public Administrator(String username, String password, Map<String, Event> events) {
        this.username = username;
        this.password = password;
        this.events = events;
    }

    @Override
    public void approveEventPoster(EventPoster eventPoster) {
        //approve an event poster
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
