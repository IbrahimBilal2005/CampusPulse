package use_case.delete_event;

/**
 * Delete Event Use case Input Boundary.
 */
public interface DeleteEventInputBoundary {

    /**
     * Execute the Delete Event Use Case.
     * @param deleteEventInputData the input data for the use case.
     */
    void deleteEvent(DeleteEventInputData deleteEventInputData);

}
