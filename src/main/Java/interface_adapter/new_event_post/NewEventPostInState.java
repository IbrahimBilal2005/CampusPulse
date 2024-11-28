package interface_adapter.new_event_post;

public class NewEventPostInState {
    private String eventName = "";
    private String eventNameError;
    private String description = "";
    private String location = "";
    private String locationError;
    private String start = "";
    private String startError;
    private String end = "";
    private String endError;
    private String tag1 = "";
    private String tag2 = "";


    public String getEventName() {
        return eventName;
    }

    public String getEventNameError() {
        return eventNameError;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationError() {
        return locationError;
    }

    public String getStart() {
        return start;
    }

    public String getStartError() {
        return startError;
    }

    public String getEnd() {
        return end;
    }

    public String getEndError() {
        return endError;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventNameError(String eventNameError) {
        this.eventNameError = eventNameError;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setStartError(String startError) {
        this.startError = startError;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setEndError(String endError) {
        this.endError = endError;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

}
