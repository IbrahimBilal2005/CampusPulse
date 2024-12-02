package interface_adapter.delete_event;

import interface_adapter.ViewManagerModel;
import use_case.delete_event.DeleteEventOutputBoundary;
import use_case.delete_event.DeleteEventOutputData;
import use_case.search.SearchOutputData;

public class DeleteEventPresenter implements DeleteEventOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final MyEventsViewModel myEventsViewModel;

    public DeleteEventPresenter(ViewManagerModel viewManagerModel,
                                MyEventsViewModel myEventsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myEventsViewModel = myEventsViewModel;
    }

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

    @Override
    public void prepareFailView(String errorMessage) {
        final MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setError(errorMessage);
        myEventsViewModel.firePropertyChanged();
    }
}
