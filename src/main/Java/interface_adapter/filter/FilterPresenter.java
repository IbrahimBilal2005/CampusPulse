package interface_adapter.filter;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import use_case.filter.FilterOutputBoundary;
import use_case.filter.FilterOutputData;

import java.util.Collections;

public class FilterPresenter implements FilterOutputBoundary {
    private final FilterViewModel filterViewModel;
    private final ViewManagerModel viewManagerModel;

    public FilterPresenter(FilterViewModel filterViewModel, ViewManagerModel viewManagerModel) {
        this.filterViewModel = filterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(FilterOutputData outputData) {
        FilterViewState state = filterViewModel.getState();
        if (outputData != null && outputData.getEvents() != null) {
            filterViewModel.getState().setFilteredEvents(outputData.getEvents());
        } else {
            filterViewModel.getState().setFilteredEvents(Collections.emptyList());
        }
        state.setFilteredEvents(outputData.getEvents()); // Update events
        state.setError(null); // Clear any previous errors
        filterViewModel.setState(state); // Apply the updated state
        viewManagerModel.firePropertyChanged(); // Notify the view
    }

    @Override
    public void setFailView(String error) {
        // Handle the failure case
        FilterViewState state = filterViewModel.getState();
        filterViewModel.setState(state);
        filterViewModel.getState().setError(error);
        viewManagerModel.firePropertyChanged(); // Notify the view to update
    }
}