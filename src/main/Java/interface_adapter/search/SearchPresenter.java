package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import java.util.Collections;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;
    private SearchOutputData outputData;
    private String errorMessage;

    public SearchPresenter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
        this.outputData = null;
        this.errorMessage = null;
    }

    @Override
    public void setPassView(SearchOutputData outputData) {
        this.outputData = outputData;
        this.errorMessage = null;  // Reset the error message when passing data
        // Update the viewModel with the events
        viewModel.setSearchResults(outputData.getEvents() != null ? outputData.getEvents() : Collections.emptyList());
        viewModel.setError(null);  // Clear the error message in the viewModel
    }

    @Override
    public void setFailView(String errorMessage) {
        this.outputData = null;  // Reset the output data when failing
        this.errorMessage = errorMessage;
        // Set the empty list for search results
        viewModel.setSearchResults(Collections.emptyList());
        // Set the error message in the viewModel
        viewModel.setError(errorMessage);
    }
}