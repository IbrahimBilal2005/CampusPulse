package interface_adapter.filter;

import interface_adapter.ViewManagerModel;
import use_case.filter.FilterOutputBoundary;
import use_case.filter.FilterOutputData;

public class FilterPresenter implements FilterOutputBoundary {
    private final FilterViewModel filterViewModel;
    private final ViewManagerModel viewManagerModel;

    public FilterPresenter(FilterViewModel filterViewModel, ViewManagerModel viewManagerModel) {
        this.filterViewModel = filterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(FilterOutputData outputData) {
        // Update the view model with the filtered events
        filterViewModel.getState().setFilteredEvents(outputData.getEvents());
        viewManagerModel.firePropertyChanged(); // Notify the view to update
    }

    @Override
    public void setFailView(String error) {
        // Handle the failure case
        filterViewModel.getState().setError(error);
        viewManagerModel.firePropertyChanged(); // Notify the view to update
    }
}