package interface_adapter.search;

import entity.Event;
import interface_adapter.ViewModel;

import java.util.List;

public class SearchViewModel extends ViewModel<SearchState> {
    public SearchViewModel(String viewName) {
        super(viewName);
    }

    public void setSearchResults(List<Event> results) {
        SearchState newState = new SearchState(results, null);
        setState(newState);  // Update state with results and no error
        firePropertyChanged();
    }

    public void setError(String errorMessage) {
        SearchState newState = new SearchState(null, errorMessage);
        setState(newState);  // Update state with error
        firePropertyChanged();
    }
}
