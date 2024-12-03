package use_case.delete_event;

import entity.AccountCreationStrategy;
import entity.Event;
import entity.EventPoster;

import java.util.ArrayList;
import java.util.List;

/**
 * The interactor class for the Delete Event use case.
 * Handles the logic for deleting an event from a specific EventPoster.
 */
public class DeleteEventInteractor implements DeleteEventInputBoundary {

    private final DeleteEventDataAccessInterface userDataAccessObject;
    private final DeleteEventOutputBoundary userPresenter;

    /**
     * Constructs a DeleteEventInteractor with the specified data access object and presenter.
     *
     * @param deleteEventDataAccessInterface the data access interface for interacting with event data.
     * @param deleteEventOutputBoundary      the output boundary to handle success or failure responses.
     */
    public DeleteEventInteractor(DeleteEventDataAccessInterface deleteEventDataAccessInterface,
                                 DeleteEventOutputBoundary deleteEventOutputBoundary) {
        this.userDataAccessObject = deleteEventDataAccessInterface;
        this.userPresenter = deleteEventOutputBoundary;
    }

    /**
     * Deletes an event from the event list of a specific EventPoster.
     * If the event does not exist, the presenter will handle a failure view.
     * Otherwise, it deletes the event and prepares a success view.
     *
     * @param deleteEventInputData the input data containing the username and the event to delete.
     */
    @Override
    public void deleteEvent(DeleteEventInputData deleteEventInputData) {
        // Check if the event exists for the given user
        if (!userDataAccessObject.eventExists(deleteEventInputData.getUsername(), deleteEventInputData.getEventToDelete())) {
            userPresenter.prepareFailView("Event does not exist.");
        } else {
            // Retrieve the EventPoster using the provided username
            final EventPoster eventPoster = userDataAccessObject.getUser(deleteEventInputData.getUsername());

            // Delete the event from the EventPoster's list of events
            userDataAccessObject.deleteEvent(eventPoster, deleteEventInputData.getEventToDelete());

            // Prepare the updated list of events
            List<Event> events = new ArrayList<>(eventPoster.getEvents().values());
            final DeleteEventOutputData deleteEventOutputData = new DeleteEventOutputData(events, false);

            // Pass the success response to the presenter
            userPresenter.prepareSuccessView(deleteEventOutputData);
        }
    }
}
