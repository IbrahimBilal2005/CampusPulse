package interface_adapter.event_details;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeScreenState;
import interface_adapter.home.HomeScreenViewModel;
import use_case.event_details.EventDetailsOutputBoundary;
import use_case.event_details.EventDetailsOutputData;

public class EventDetailsPresenter implements EventDetailsOutputBoundary {
    private final EventDetailsViewModel eventViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeScreenViewModel homeScreenViewModel;

    public EventDetailsPresenter(EventDetailsViewModel viewModel, EventDetailsViewModel eventViewModel, ViewManagerModel viewManagerModel, HomeScreenViewModel homeScreenViewModel){
        this.eventViewModel = eventViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeScreenViewModel = homeScreenViewModel;
    }


    public void prepareSuccessView(EventDetailsOutputData data){
        if (!data.isClick()) {
            final HomeScreenState homeScreenState = homeScreenViewModel.getState();
            this.homeScreenViewModel.setState(homeScreenState);
            this.viewManagerModel.setState(homeScreenViewModel.getViewName());
        }
    }
}
