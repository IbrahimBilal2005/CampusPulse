package use_case.event_details;

/**
 * Event Details Interactor
 * Since no major logic is required the execute function essentially passes on to the presenter class to change the view
 */
public class EventDetailsInteractor implements EventDetailsInputBoundary{
    private final EventDetailsOutputBoundary eventDetailsPresenter;

    public EventDetailsInteractor(EventDetailsOutputBoundary eventDetailsOutputBoundary) {
        this.eventDetailsPresenter = eventDetailsOutputBoundary;
    }

    @Override
    public void execute(EventDetailsInputData output) {
        EventDetailsOutputData outputData = new EventDetailsOutputData(output.getClick());
        eventDetailsPresenter.changeView(outputData);
    }
}
