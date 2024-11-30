package interface_adapter.delete_event;

import interface_adapter.ViewModel;

/**
 * View Model for the My Events View.
 */
public class MyEventsViewModel extends ViewModel<MyEventsState> {

    public MyEventsViewModel() {
        super("My Events");
        setState(new MyEventsState());
    }
}
