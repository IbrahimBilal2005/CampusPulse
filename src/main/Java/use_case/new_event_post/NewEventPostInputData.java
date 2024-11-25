package use_case.new_event_post;

import java.util.List;

public class NewEventPostInputData {
    private final String eventName;
    private final String description;
    private final String location;
    private final String start;
    private final String end;
    private final List<String> tags;
    public NewEventPostInputData(String username, String eventName, String description,
                                 String location, String start, String end, List<String> tags) {
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.tags = tags;
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
