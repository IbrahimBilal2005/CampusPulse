package use_case.home;

public interface HomeScreenOutputBoundary {
    void presentHomeScreen(HomeScreenOutputData outputData);
    void presentEventDetails(HomeScreenEventOutputData outputData); // New method for event details
}
