package interface_adapter.delete_event;

import interface_adapter.ViewManagerModel;
import use_case.delete_event.DeleteEventOutputBoundary;
import use_case.delete_event.DeleteEventOutputData;

public class DeleteEventPresenter implements DeleteEventOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DeleteEventViewModel deleteEventViewModel;
    private final MyEventsViewModel myEventsViewModel;

    public DeleteEventPresenter(ViewManagerModel viewManagerModel,
                                MyEventsViewModel myEventsViewModel,
                                DeleteEventViewModel deleteEventViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myEventsViewModel = myEventsViewModel;
        this.deleteEventViewModel = deleteEventViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteEventOutputData updatedEventsData) {
        // on success, show the updated "my events" view
        final MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setEvents(updatedEventsData.getEvents());
        this.myEventsViewModel.setState(myEventsState);
        this.myEventsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(myEventsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setError(errorMessage);
        myEventsViewModel.firePropertyChanged();
    }
}
