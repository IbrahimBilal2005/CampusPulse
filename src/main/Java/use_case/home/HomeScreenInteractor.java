package use_case.home;

import entity.User;
import use_case.home.HomeScreenDataAccessInterface;

public class HomeScreenInteractor implements HomeScreenInputBoundary {
    private final HomeScreenDataAccessInterface dataAccess;
    private final HomeScreenOutputBoundary outputBoundary;

    public HomeScreenInteractor(HomeScreenDataAccessInterface dataAccess, HomeScreenOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void handleRequest(HomeScreenInputData inputData) {
        User user = inputData.getUser();
        boolean isEventPoster = user.isEventPoster();
        var events = dataAccess.fetchEvents(user);

        HomeScreenOutputData outputData = new HomeScreenOutputData(isEventPoster, events);
        outputBoundary.presentHomeScreen(outputData);
    }
}
