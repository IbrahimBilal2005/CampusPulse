package interface_adapter.delete_event;

import entity.Event;
import use_case.delete_event.DeleteEventInputBoundary;
import use_case.delete_event.DeleteEventInputData;

/**
 * Controller for the Delete Event Use Case
 */
public class DeleteEventController {
    private final DeleteEventInputBoundary deleteEventUseCaseInteractor;

    public DeleteEventController(DeleteEventInputBoundary deleteEventUseCaseInteractor) {
        this.deleteEventUseCaseInteractor = deleteEventUseCaseInteractor;
    }

    /**
     * Executes the delete event use case
     * @param username the user whose event to delete
     * @param eventToDelete the event to delete
     */
    public void deleteEvent(String username, Event eventToDelete) {
        final DeleteEventInputData deleteEventInputData = new DeleteEventInputData(username, eventToDelete);

        deleteEventUseCaseInteractor.deleteEvent(deleteEventInputData);
    }
}
