package interface_adapter.search;

import entity.Event;
import interface_adapter.ViewModel;

import java.util.Collections;
import java.util.List;

public class SearchViewModel extends ViewModel<SearchState> {
    public SearchViewModel(String viewName) {
        super(viewName);
        // Initialize with a default empty state to avoid null issues
        setState(new SearchState(Collections.emptyList(), null));
    }

    public void setSearchResults(List<Event> results) {
        // Use the setter to update the results in the state
        SearchState newState = getState();  // Get the current state
        newState.setResults(results);  // Set the new results
        setState(newState);  // Update the state
        firePropertyChanged();  // Notify listeners about the state change
    }

    public void setError(String errorMessage) {
        // Use the setter to update the error in the state
        SearchState newState = getState();  // Get the current state
        newState.setError(errorMessage);  // Set the new error message
        setState(newState);  // Update the state
        firePropertyChanged();  // Notify listeners about the error
    }
}
