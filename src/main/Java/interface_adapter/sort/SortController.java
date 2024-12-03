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

    public SortController(SortInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void sort(String query, List<Event> filteredEvents) {
        SortInputData inputData = new SortInputData(query, filteredEvents);
        inputBoundary.sort(inputData);
    }
}