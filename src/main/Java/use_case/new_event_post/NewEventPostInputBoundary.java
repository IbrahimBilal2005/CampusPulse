package use_case.new_event_post;

public interface NewEventPostInputBoundary {
    void execute(NewEventPostInputData inputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToHomeView();

}
