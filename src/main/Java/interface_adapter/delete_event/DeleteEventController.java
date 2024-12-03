package interface_adapter.delete_event;

import entity.Event;
import use_case.delete_event.DeleteEventInputBoundary;
import use_case.delete_event.DeleteEventInputData;

/**
 * Controller for the Delete Event use case.
 * Acts as a mediator between the user interface and the use case interactor.
 */
public class DeleteEventController {
    private final DeleteEventInputBoundary deleteEventUseCaseInteractor;

    /**
     * Constructs a DeleteEventController with the specified interactor.
     *
     * @param deleteEventUseCaseInteractor the interactor handling the Delete Event use case.
     */
    public DeleteEventController(DeleteEventInputBoundary deleteEventUseCaseInteractor) {
        this.deleteEventUseCaseInteractor = deleteEventUseCaseInteractor;
    }

    /**
     * Executes the delete event use case by creating input data and passing it to the interactor.
     *
     * @param username      the username of the event poster whose event is to be deleted.
     * @param eventToDelete the event to be deleted.
     */
    public void deleteEvent(String username, Event eventToDelete) {
        final DeleteEventInputData deleteEventInputData = new DeleteEventInputData(username, eventToDelete);
        deleteEventUseCaseInteractor.deleteEvent(deleteEventInputData);
    }
}
