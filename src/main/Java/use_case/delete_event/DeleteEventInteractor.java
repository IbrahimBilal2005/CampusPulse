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
    private final AccountCreationStrategy accountCreator;

    public DeleteEventInteractor(DeleteEventDataAccessInterface deleteEventDataAccessInterface,
                            DeleteEventOutputBoundary deleteEventOutputBoundary,
                            AccountCreationStrategy accountCreator) {
        this.userDataAccessObject = deleteEventDataAccessInterface;
        this.userPresenter = deleteEventOutputBoundary;
        this.accountCreator = accountCreator;

    }

    @Override
    public void deleteEvent(DeleteEventInputData deleteEventInputData) {
        // Get the eventPoster who's username mathces the input username
        final EventPoster eventPoster = userDataAccessObject.getUser(deleteEventInputData.getUsername());

        // Delete the event from the eventposter's events.
        userDataAccessObject.deleteEvent(eventPoster, deleteEventInputData.getEventToDelete());

        List<Event> events = new ArrayList<>(eventPoster.getEvents().values());
        final DeleteEventOutputData deleteEventOutputData = new DeleteEventOutputData(events, false);

        userPresenter.prepareSuccessView(deleteEventOutputData);
    }
}
