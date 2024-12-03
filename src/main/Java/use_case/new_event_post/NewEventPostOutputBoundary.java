package use_case.new_event_post;

/**
 * The output boundary for the New Event Post Use Case.
 */

public interface NewEventPostOutputBoundary {

    /**
     * Prepares the success view for the New Event Post Use Case.
     * @param outputData the output data
     */
    void presentSuccess(NewEventPostOutputData outputData);

    /**
     * Prepares the failure view for the New Event Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    void presentFailure(String errorMessage);
}
