package entity;

import java.util.List;

/**
 * An event in our program.
 */
public class Event {
    private final Integer id;
    private final String name;
    private final String description;
    private final String location;
    private final String date;
    private final String time;
    private final List<String> tags;

    public Event(Integer id,
                 String name,
                 String description,
                 String location,
                 String date,
                 String time,
                 List<String> tags) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.tags = tags;
    }

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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<String> getTags() {
        return tags;
    }
}
