package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * An event in our program.
 */
public class Event {
    private final String name;
    private final String description;
    private final String location;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final List<String> tags;

    public Event(String name,
                 String description,
                 String location,
                 LocalDateTime start,
                 LocalDateTime end,
                 List<String> tags) {

        this.name = name;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public List<String> getTags() {
        return tags;
    }
}
