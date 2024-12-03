package use_case.sort;

/**
 * The output boundary for the Sort Use Case.
 */
public interface SortOutputBoundary {

    /**
     * Prepares the success view for the Sort Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SortOutputData outputData);

    /**
     * Prepares the failure view for the Sort Use Case.
     * @param error the error message
     */
    void prepareFailView(String error);
}