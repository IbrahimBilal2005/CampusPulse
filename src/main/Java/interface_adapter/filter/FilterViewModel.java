package interface_adapter.filter;

import entity.Event;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class FilterViewModel extends ViewModel<FilterViewState> {
    private final PropertyChangeSupport support;
    public FilterViewModel() {
        super("Filter Screen");
        setState(new FilterViewState());
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}