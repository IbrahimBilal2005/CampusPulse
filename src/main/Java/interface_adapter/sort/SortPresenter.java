package interface_adapter.sort;

import interface_adapter.ViewManagerModel;
import use_case.sort.SortOutputBoundary;
import use_case.sort.SortOutputData;

import java.util.Collections;

/**
 * Presenter for the sort use case.
 */
public class SortPresenter implements SortOutputBoundary {
    private final SortViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Creates a sort presenter based on the specified viewModel and viewManagerModel
     * @param viewModel the viewModel
     * @param viewManagerModel the viewManagerModel
     */
    public SortPresenter(SortViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare the success view with the relevant output data.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SortOutputData outputData) {
        SortState state = viewModel.getState();
        if (outputData != null && outputData.getSortedEvents() != null) {
            viewModel.getState().setSortedResult(outputData.getSortedEvents());
        } else {
            viewModel.getState().setSortedResult(Collections.emptyList());
        }
        viewModel.setState(state);
        state.setError(null);
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare the fail view with the relevant error.
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        SortState state = viewModel.getState();
        state.setError(error);
        viewModel.setState(state);
        state.setSortedResult(null);
        viewManagerModel.firePropertyChanged();
    }
}
