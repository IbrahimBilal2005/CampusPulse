package interface_adapter.sort;

import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SortViewModel {

    private final PropertyChangeSupport support;
    private SortState state;

    public SortViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new SortState();
    }

    public SortState getState() {
        return state;
    }

    public void setState(SortState newstate) {
        SortState oldState = this.state;
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