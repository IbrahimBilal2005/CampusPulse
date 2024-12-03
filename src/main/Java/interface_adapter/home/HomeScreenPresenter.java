package interface_adapter.home;

import use_case.home.HomeScreenOutputBoundary;
import use_case.home.HomeScreenOutputData;
import use_case.home.HomeScreenEventOutputData;

public class HomeScreenPresenter implements HomeScreenOutputBoundary {
    private final HomeScreenViewModel viewModel;

    public HomeScreenPresenter(HomeScreenViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Presents the main Home Screen data, including the user's role and events.
     *
     * @param outputData The data for rendering the Home Screen.
     */
    @Override
    public void presentHomeScreen(HomeScreenOutputData outputData) {
//        viewModel.setEventPoster(outputData.isEventPoster());
//        viewModel.setEvents(outputData.getEvents());
    }

    /**
     * Presents details for a specific clicked event.
     *
     * @param eventOutputData The data for rendering the Event Details screen.
     */
    @Override
    public void presentEventDetails(HomeScreenEventOutputData eventOutputData) {
//        viewModel.setSelectedEvent(eventOutputData.getEvent()); // Set the clicked event
//        viewModel.switchToEventDetailsScreen(); // Trigger the UI to switch to Event Details
    }
}
