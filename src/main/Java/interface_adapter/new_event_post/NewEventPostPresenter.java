package interface_adapter.new_event_post;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete_event.MyEventsState;
import interface_adapter.delete_event.MyEventsViewModel;
import use_case.new_event_post.NewEventPostOutputBoundary;
import use_case.new_event_post.NewEventPostOutputData;

/**
 * The presenter for the New Event Post Use Case.
 */

public class NewEventPostPresenter implements NewEventPostOutputBoundary {
    private final NewEventViewModel newEventViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyEventsViewModel myEventsViewModel;

    public NewEventPostPresenter(final NewEventViewModel newEventViewModel,
                                 final ViewManagerModel viewManagerModel,
                                 final MyEventsViewModel myEventsViewModel) {
        this.newEventViewModel = newEventViewModel;
        this.viewManagerModel = viewManagerModel;
        this.myEventsViewModel = myEventsViewModel;

    }

    @Override
    public void presentSuccess(NewEventPostOutputData outputData) {
        MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.getEvents().add(outputData.getNewevent());  // Assuming `newEvent` is the event created in the interactor
        this.myEventsViewModel.setState(myEventsState);
        this.myEventsViewModel.firePropertyChanged();

        viewManagerModel.setState(myEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void presentFailure(String errorMessage) {
        final NewEventPostInState currentState = newEventViewModel.getState();

        if (errorMessage.contains("Event name")) {
            currentState.setEventNameError(errorMessage);
        } else if (errorMessage.contains("Location")) {
            currentState.setLocationError(errorMessage);
        } else if (errorMessage.contains("Start time")) {
            currentState.setStartError(errorMessage);
        } else if (errorMessage.contains("End time")) {
            currentState.setEndError(errorMessage);
        }
        newEventViewModel.firePropertyChanged(); // Notify the UI
    }

}
