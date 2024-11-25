package use_case.new_event_post;

import java.util.List;

public class NewEventPostInputData {
    private final String username;
    private final Integer eventId;
    private final String eventName;
    private final String description;
    private final String location;
    private final String start;
    private final String end;
    private final List<String> tags;
    public NewEventPostInputData(String username, Integer eventId, String eventName, String description,
                                 String location, String start, String end, List<String> tags) {
        this.username = username;
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.tags = tags;
    }
    public String getUsername() {
        return username;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public List<String> getTags() {
        return tags;
    }

}
