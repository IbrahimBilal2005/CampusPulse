package interface_adapter.new_event_post;

import interface_adapter.ViewManagerModel;
import use_case.new_event_post.NewEventPostOutputBoundary;
import use_case.new_event_post.NewEventPostOutputData;

/**
 * The presenter for the New Event Post Use Case.
 */

public class NewEventPostPresenter implements NewEventPostOutputBoundary {
    private final NewEventViewModel newEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public NewEventPostPresenter(final NewEventViewModel newEventViewModel,
                                 final ViewManagerModel viewManagerModel) {
        this.newEventViewModel = newEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentSuccess(NewEventPostOutputData outputData) {
        // Switch to home view
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

    @Override
    public void switchToHomeView() {
        // TODO viewManagerModel.setState(HomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
