package use_case.new_event_post;

public interface NewEventPostOutputBoundary {
    void presentSuccess(NewEventPostOutputData outputData);
    void presentFailure(String errorMessage);
    void switchToHomeView();
}
