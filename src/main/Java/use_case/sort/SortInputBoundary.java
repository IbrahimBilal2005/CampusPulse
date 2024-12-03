package use_case.sort;

/**
 * Input boundary for sort use case.
 */
public interface SortInputBoundary {

    /**
     * Executes the sort use case.
     * @param inputData sort input data.
     */
    void sort(SortInputData inputData);
}