package interface_adapter.sort;

import interface_adapter.ViewManagerModel;
import use_case.sort.SortOutputBoundary;
import use_case.sort.SortOutputData;

import java.util.Collections;

public class SortPresenter implements SortOutputBoundary {
    private final SortViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SortPresenter(SortViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(SortOutputData outputData) {
        SortState state = viewModel.getState();
        if (outputData != null && outputData.getSortedEvents() != null) {
            viewModel.getState().setSortedResults(outputData.getSortedEvents());
        } else {
            viewModel.getState().setSortedResults(Collections.emptyList());
        }
        viewModel.setState(state);
        state.setError(null);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void setFailView(String error) {
        SortState state = viewModel.getState();
        state.setError(error);
        viewModel.setState(state);
        state.setSortedResults(null);
        viewManagerModel.firePropertyChanged();
    }
}
