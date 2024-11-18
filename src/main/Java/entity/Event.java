package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * An event in our program.
 */
public class Event {
    private final Integer id;
    private final String name;
    private final String description;
    private final String location;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final List<String> tags;

    public Event(Integer id,
                 String name,
                 String description,
                 String location,
                 LocalDateTime start,
                 LocalDateTime end,
                 List<String> tags) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.tags = tags;
    }

    // TODO identification system.
    //  Do we need a unique id for each event in the event posters events?
    //  Is the name/title enough?

    public Integer getId(){
        return id;
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

    // TODO discuss how we want to represent start/end date and start/end time.
    //  LocalDateTime represents time and date data independent of time zone.
    //  We could separate each component, represent it as Strings/Ints etc.
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
