package interface_adapter.filter;

import entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public class FilterViewState {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> tags;
    private String location;
    private List<Event> filteredEvents; // New property for filtered events
    private String error; // New property for error messages

    // Getters and setters for state properties
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Event> getFilteredEvents() {
        return filteredEvents;
    }

    public void setFilteredEvents(List<Event> filteredEvents) {
        this.filteredEvents = filteredEvents;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}