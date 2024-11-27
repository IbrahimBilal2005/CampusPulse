package interface_adapter.filter;

import interface_adapter.ViewModel;

public class FilterViewModel extends ViewModel<FilterViewState> {
    public FilterViewModel() {
        super("Filter Screen");
        setState(new FilterViewState());
    }
}