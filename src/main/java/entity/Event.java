package entity;

/**
 * Class representing a single Event in CampusPulse
 */
public class Event {
    private String id;
    private String title;
    private String date;
    private String location;
    private String description;

    public Event(String id, String title, String date, String location, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
