package interface_adapter.home;

import use_case.home.HomeScreenInputBoundary;
import use_case.home.HomeScreenInputData;
import use_case.home.HomeScreenEventInputData;

import entity.User;
import entity.Event;

public class HomeScreenController {
    private final HomeScreenInputBoundary inputBoundary;

    public HomeScreenController(HomeScreenInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles fetching the user's events to display on the home screen.
     *
     * @param user The currently logged-in user.
     */
    public void fetchMyEvents(User user) {
        HomeScreenInputData inputData = new HomeScreenInputData(user);
        inputBoundary.loadMyEvents(inputData); // Triggers interactor logic for "My Events"
    }

    /**
     * Handles fetching details for a specific event when clicked.
     *
     * @param event The event that the user clicked on.
     */
    public void fetchEventDetails(Event event) {
        HomeScreenEventInputData inputData = new HomeScreenEventInputData(event);
        inputBoundary.loadEventDetails(inputData); // Triggers interactor logic for event details
    }
}
