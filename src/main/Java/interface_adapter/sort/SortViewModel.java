package interface_adapter.sort;

import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for the sort use case.
 */
public class SortViewModel {

    private final PropertyChangeSupport support;
    private SortState state;

    /**
     * Creates a SortViewModel.
     */
    public SortViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new SortState();
    }

    /**
     * Get the state.
     * @return state.
     */
    public SortState getState() {
        return state;
    }

    /**
     * Set the state
     * @param newstate the new state to set.
     */
    public void setState(SortState newstate) {
        SortState oldState = this.state;
        this.state = newstate;
        support.firePropertyChange("state", oldState, newstate);
    }

    /**
     * Add property change listener.
     * @param listener the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * remove property change listener.
     * @param listener the listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}