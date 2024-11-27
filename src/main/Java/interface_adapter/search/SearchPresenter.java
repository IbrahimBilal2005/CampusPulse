package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(SearchOutputData outputData) {
        // Update state with search results
        SearchState state = viewModel.getState();
        state.setResults(outputData.getEvents());
        viewModel.setState(state);
        state.setError(null); // Clear any previous errors
        viewManagerModel.firePropertyChanged(); // Notify observers about state change
    }

    @Override
    public void setFailView(String errorMessage) {
        // Update state with an error message
        SearchState state = viewModel.getState();
        state.setError(errorMessage);
        viewModel.setState(state);
        state.setResults(null); // Clear any previous results
        viewManagerModel.firePropertyChanged(); // Notify observers about state change
    }
}
