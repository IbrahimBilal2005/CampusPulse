package use_case.delete_event;

import entity.Event;

public class DeleteEventInputData {
    private final String username;
    private final Event eventToDelete;

    public DeleteEventInputData(String username, Event eventToDelete) {
        this.username = username;
        this.eventToDelete = eventToDelete;
    }

    String getUsername() { return username; }

    String getEventToDelete() { return eventToDelete.toString(); }
}
