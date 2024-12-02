package use_case.event_details;

import use_case.event_details.EventDetailsOutputData;

public interface EventDetailsOutputBoundary {

    void prepareSuccessView(EventDetailsOutputData data);
}
