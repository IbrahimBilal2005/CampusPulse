package use_case.event_details;

/**
 * Event Details Interactor
 * Since no major logic is required the execute function essentially passes on to the presenter class to change the view
 */
public class EventDetailsInteractor implements EventDetailsInputBoundary{
    private final EventDetailsOutputBoundary eventDetailsPresenter;

    public EventDetailsInteractor(EventDetailsOutputBoundary eventDetailsPresenter) {
        this.eventDetailsPresenter = eventDetailsPresenter;
    }

    @Override
    public void execute() {
        EventDetailsOutputData outputData = new EventDetailsOutputData(true);
        eventDetailsPresenter.changeView(outputData);
    }
}
