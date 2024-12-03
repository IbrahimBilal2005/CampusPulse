package use_case.home;

import entity.Event;
import use_case.home.HomeScreenDataAccessInterface;

public class HomeScreenInteractor implements HomeScreenInputBoundary {
    private final HomeScreenDataAccessInterface dataAccess;
    private final HomeScreenOutputBoundary outputBoundary;

    public HomeScreenInteractor(HomeScreenDataAccessInterface dataAccess, HomeScreenOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void loadMyEvents(HomeScreenInputData inputData) {
//        boolean isEventPoster = inputData.getUser().isEventPoster();
//        var events = dataAccess.fetchEvents(inputData.getUser());
//        HomeScreenOutputData outputData = new HomeScreenOutputData(isEventPoster, events);
//        outputBoundary.presentHomeScreen(outputData);
    }

    @Override
    public void loadEventDetails(HomeScreenEventInputData inputData) {
        Event event = inputData.getEvent();
        HomeScreenEventOutputData outputData = new HomeScreenEventOutputData(event);
        outputBoundary.presentEventDetails(outputData);
    }
}
