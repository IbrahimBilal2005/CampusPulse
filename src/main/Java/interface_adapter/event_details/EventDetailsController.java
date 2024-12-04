package interface_adapter.event_details;

import use_case.event_details.EventDetailsInputBoundary;
import use_case.event_details.EventDetailsInputData;

/**
 * The controller for the event details use case
 */
public class EventDetailsController {
    private final EventDetailsInputBoundary eventDetailsInteractor;

    public EventDetailsController(EventDetailsInputBoundary eventDetailsInputBoundary) {
        this.eventDetailsInteractor = eventDetailsInputBoundary;
    }

    /**
     * Executes the event details use case
     */
    public void execute() {
        final EventDetailsInputData eventDetailsInput = new EventDetailsInputData(true);
        eventDetailsInteractor.execute(eventDetailsInput);
    }
}
