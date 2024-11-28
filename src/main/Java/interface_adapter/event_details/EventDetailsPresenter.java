package interface_adapter.event_details;

import use_case.event_details.EventDetailsOutputBoundary;
import use_case.event_details.EventDetailsOutputData;

public class EventDetailsPresenter implements EventDetailsOutputBoundary {
    private final EventDetailsViewModel viewModel;

    public EventDetailsPresenter(EventDetailsViewModel viewModel){
        this.viewModel = viewModel;
    }


    public void prepareSuccessView(EventDetailsOutputData data){
        if (!data.isClick()) {
//      TODO viewManagerModel.setState(homeViewModel.getViewName());
        }
    }
}
