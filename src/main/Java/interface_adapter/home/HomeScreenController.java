package interface_adapter.home;

import use_case.home.HomeScreenInputBoundary;
import use_case.home.HomeScreenInputData;

import entity.User;

public class HomeScreenController {
    private final HomeScreenInputBoundary inputBoundary;

    public HomeScreenController(HomeScreenInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleRequest(User user) {
        HomeScreenInputData inputData = new HomeScreenInputData(user);
        inputBoundary.handleRequest(inputData);
    }
}
