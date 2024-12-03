package interface_adapter.delete_event;

import interface_adapter.ViewManagerModel;
import use_case.delete_event.DeleteEventOutputBoundary;
import use_case.delete_event.DeleteEventOutputData;

/**
 * Presenter for the Delete Event use case.
 * Updates the view models and triggers UI changes based on the output of the Delete Event use case.
 */
public class DeleteEventPresenter implements DeleteEventOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final MyEventsViewModel myEventsViewModel;

    /**
     * Constructs a DeleteEventPresenter with the specified view manager model and MyEvents view model.
     *
     * @param viewManagerModel the model responsible for managing application views.
     * @param myEventsViewModel the view model for managing the state of the "My Events" view.
     */
    public DeleteEventPresenter(ViewManagerModel viewManagerModel,
                                MyEventsViewModel myEventsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myEventsViewModel = myEventsViewModel;
    }

    /**
     * Prepares the success view when the event deletion is successful.
     * Updates the "My Events" state with the updated event list and clears any error messages.
     *
     * @param updatedEventsData the output data containing the updated list of events.
     */
    @Override
    public void prepareSuccessView(DeleteEventOutputData updatedEventsData) {
        final MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setEvents(updatedEventsData.getEvents());
        myEventsState.setError("");
        this.myEventsViewModel.setState(myEventsState);
        this.myEventsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(myEventsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view when the event deletion fails.
     * Updates the "My Events" state with the provided error message.
     *
     * @param errorMessage the error message to display in the view.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setError(errorMessage);
        myEventsViewModel.firePropertyChanged();
    }
}
