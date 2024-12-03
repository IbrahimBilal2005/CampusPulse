package interface_adapter.home;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeScreenViewModel extends ViewModel<HomeScreenState> {

    private final PropertyChangeSupport support;
    private HomeScreenState state;

    public HomeScreenViewModel() {
        super("Home_screen");
        this.support = new PropertyChangeSupport(this);
        setState(new HomeScreenState());
        this.state = new HomeScreenState();
    }

    public HomeScreenState getState() {
        return state;
    }

    public void setState(HomeScreenState newstate) {
        HomeScreenState oldState = this.state;
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

