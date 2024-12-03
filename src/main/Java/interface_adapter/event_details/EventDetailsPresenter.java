package interface_adapter.event_details;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeScreenState;
import interface_adapter.home.HomeScreenViewModel;
import use_case.event_details.EventDetailsOutputBoundary;
import use_case.event_details.EventDetailsOutputData;

/**
 * Presenter for Event Details use case
 */
public class EventDetailsPresenter implements EventDetailsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeScreenViewModel homeScreenViewModel;

    public EventDetailsPresenter(ViewManagerModel viewManagerModel, HomeScreenViewModel homeScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeScreenViewModel = homeScreenViewModel;
    }

    @Override
    public void changeView(EventDetailsOutputData data){
        if (!data.isClick()) {
            final HomeScreenState homeScreenState = homeScreenViewModel.getState();
            this.homeScreenViewModel.setState(homeScreenState);
            this.viewManagerModel.setState(homeScreenViewModel.getViewName());
        }
    }
}
