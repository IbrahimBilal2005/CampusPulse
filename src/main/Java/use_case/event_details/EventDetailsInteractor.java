package use_case.event_details;

public class EventDetailsInteractor implements EventDetailsInputBoundary{

    private final EventDetailsInputBoundary eventDetailsPresenter;

    public EventDetailsInteractor(EventDetailsInputBoundary eventDetailsInputBoundary) {
        this.eventDetailsPresenter = eventDetailsInputBoundary;
    }

    @Override
    public void execute() {
        EventDetailsOutputData outputData = new EventDetailsOutputData(true);
        eventDetailsPresenter.execute();
    }
}
