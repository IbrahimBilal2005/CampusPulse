package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import java.util.Collections;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;

    public SearchPresenter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void setPassView(SearchOutputData outputData) {
        viewModel.setResults(outputData.getEvents());
        viewModel.setError(null);
    }

    @Override
    public void setFailView(String errorMessage) {
        viewModel.setResults(Collections.emptyList());
        viewModel.setError(errorMessage);
    }
}
