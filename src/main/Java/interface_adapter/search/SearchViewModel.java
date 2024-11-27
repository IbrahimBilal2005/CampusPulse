package interface_adapter.search;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel {

    private final PropertyChangeSupport support;
    private SearchState state;

    public SearchViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new SearchState();
    }

    public SearchState getState() {
        return state;
    }

    public void setState(SearchState newstate) {
        SearchState oldState = this.state;
        this.state = newstate;
        support.firePropertyChange("state", oldState, newstate);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
