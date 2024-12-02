package interface_adapter.event_details;

import use_case.event_details.EventDetailsInputBoundary;
import use_case.event_details.EventDetailsInputData;

public class EventDetailsController {
    private final EventDetailsInputBoundary eventDetailsInteractor;

    public EventDetailsController(EventDetailsInputBoundary eventDetailsInputBoundary) {
        this.eventDetailsInteractor = eventDetailsInputBoundary;
    }

    public void execute() {
        final EventDetailsInputData eventDetailsInput = new EventDetailsInputData();
        eventDetailsInteractor.execute();
    }
}
