package interface_adapter.new_event_post;

import interface_adapter.ViewModel;

public class NewEventViewModel extends ViewModel<NewEventPostInState> {
    public NewEventViewModel() {
        super("NewEventPostViewModel");
        // Initialize the state with default values
        this.setState(new NewEventPostInState());
    }

    /**
     * Updates the ViewModel's state with new data and notifies observers.
     * @param newState The updated state
     */
    @Override
    public void setState(NewEventPostInState newState) {
        super.setState(newState);
        // Fire property change event to notify listeners (e.g., the UI)
        this.firePropertyChanged("state");
    }
}
