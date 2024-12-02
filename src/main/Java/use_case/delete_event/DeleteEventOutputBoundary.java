package use_case.delete_event;

/**
 * Output boundary for the Delete event use case.
 */
public interface DeleteEventOutputBoundary {

    /**
     * Prepares the success view for the Delete Event use case.
     * @param outputData the output data
     */
    void prepareSuccessView(DeleteEventOutputData outputData);

    /**
     * Prepares the fail view for the Delete Event use case.
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);
}
