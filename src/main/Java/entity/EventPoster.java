package entity;

import java.util.List;
import java.util.Map;

/**
 * An Event Posters in our program.
 */
public class EventPoster implements Account{
    private final String username;
    private  String password;
    private final String organizationName;
    private final String sopLink;
    private final List<String> tags;
    private final Map<Integer, Event> events;

    public EventPoster(String username,
                       String password,
                       String organizationName,
                       String sopLink,
                       List<String> tags,
                       Map<Integer, Event> events) {

        this.username = username;
        this.password = password;
        this.organizationName = organizationName;
        this.sopLink = sopLink;
        this.tags = tags;

        // TODO discuss how we want to keep track of each Event Posters Event (My Event Logic)
        //  Also discuss what the keys should be (id, username, event title, etc)
        this.events = events;

    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public String getSopLink() {
        return this.sopLink;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public Map<Integer, Event> getEvents() {
        return this.events;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
