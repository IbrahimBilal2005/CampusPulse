package use_case.home;

public interface HomeScreenInputBoundary {
    void loadMyEvents(HomeScreenInputData inputData); // For fetching user events
    void loadEventDetails(HomeScreenEventInputData inputData); // For fetching event details
}
