package use_case.event_details;

/**
 * The output boundary for the event details use case
 */
public interface EventDetailsOutputBoundary {
    /**
     * changes the view to the new view in data
     * @param data the output data
     */
    void changeView(EventDetailsOutputData data);
}
