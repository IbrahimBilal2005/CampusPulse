package interface_adapter.search;

import entity.Event;
import java.util.List;

public class SearchState {
    private final List<Event> events;
    private final String errorMessage;

    public SearchState(List<Event> events, String errorMessage) {
        this.events = events;
        this.errorMessage = errorMessage;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
