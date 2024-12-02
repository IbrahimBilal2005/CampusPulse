package interface_adapter.home;

import use_case.home.HomeScreenOutputBoundary;
import use_case.home.HomeScreenOutputData;

public class HomeScreenPresenter implements HomeScreenOutputBoundary {
    private final HomeScreenViewModel viewModel;

    public HomeScreenPresenter(HomeScreenViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentHomeScreen(HomeScreenOutputData outputData) {
        viewModel.setEventPoster(outputData.isEventPoster());
        viewModel.setEvents(outputData.getEvents());
    }
}
