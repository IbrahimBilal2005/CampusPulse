package use_case.event_details;

/**
 * Input Boundary for actions which are related to looking at an event
 */
public interface EventDetailsInputBoundary {

    /**
     * Executes the event details use case
     */
    void execute(EventDetailsInputData eventDetailsInputData);
}
