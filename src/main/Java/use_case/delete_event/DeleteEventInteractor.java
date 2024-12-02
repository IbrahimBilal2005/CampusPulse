package use_case.delete_event;

import entity.AccountCreationStrategy;
import entity.Event;
import entity.EventPoster;
import use_case.search.SearchOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * The Delete Event interactor.
 */
public class DeleteEventInteractor implements DeleteEventInputBoundary{
    private final DeleteEventDataAccessInterface userDataAccessObject;
    private final DeleteEventOutputBoundary userPresenter;

    public DeleteEventInteractor(DeleteEventDataAccessInterface deleteEventDataAccessInterface,
                            DeleteEventOutputBoundary deleteEventOutputBoundary) {
        this.userDataAccessObject = deleteEventDataAccessInterface;
        this.userPresenter = deleteEventOutputBoundary;

    }

    @Override
    public void deleteEvent(DeleteEventInputData deleteEventInputData) {
        if (!userDataAccessObject.eventExists(deleteEventInputData.getUsername(), deleteEventInputData.getEventToDelete())){
            userPresenter.prepareFailView("Event does not exist.");
        }
        else {
            // Get the eventPoster whose username matches the input username
            final EventPoster eventPoster = (EventPoster )userDataAccessObject.getEventPoster(deleteEventInputData.getUsername());

            // Delete the event from the eventposter's events.
            userDataAccessObject.deleteEvent(eventPoster, deleteEventInputData.getEventToDelete());

            List<Event> events = new ArrayList<>(eventPoster.getEvents().values());
            final DeleteEventOutputData deleteEventOutputData = new DeleteEventOutputData(events, false);

            userPresenter.prepareSuccessView(deleteEventOutputData);
        }

    }
}
