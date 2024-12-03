package interface_adapter.sort;

import entity.Event;
import use_case.sort.SortInputBoundary;
import use_case.sort.SortInputData;

import java.util.List;

/**
 * The Controller for the sort use case
 */
public class SortController {

    private final SortInputBoundary inputBoundary;

    /**
     *  Create a SortController based on the specified inputBoundary
     * @param inputBoundary the input boundary
     */
    public SortController(SortInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Set up the relevant input data and execute the sort interactor
     * @param query the query to sort the list by.
     * @param currentEvents the current events to sort.
     */
    public void sort(String query, List<Event> currentEvents) {
        SortInputData inputData = new SortInputData(query, currentEvents);
        inputBoundary.sort(inputData);
    }
}