package use_case.new_event_post;


public class NewEventPostInputData {
    private final String username;
    private final String eventName;
    private final String description;
    private final String location;
    private final String start;
    private final String end;
    private final String tag1;
    private final String tag2;

    public NewEventPostInputData(String username, String eventName, String description,
                                 String location, String start, String end, String tag1, String tag2) {
        this.username = username;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public String getUsername() {return username;}

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

    public String getTag1() {return tag1;}

    public String getTag2() {return tag2;}

}
