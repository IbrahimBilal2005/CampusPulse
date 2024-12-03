package use_case.event_details;

/**
 * Event Details Interactor
 * Since no major logic is required the execute function essentially passes on to the presenter class to change the view
 */
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
