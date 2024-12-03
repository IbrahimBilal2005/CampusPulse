package use_case.new_event_post;

/**
 * The input boundary for the New Event Post Use Case.
 */

public interface NewEventPostInputBoundary {
    void execute(NewEventPostInputData inputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToHomeView();

}
