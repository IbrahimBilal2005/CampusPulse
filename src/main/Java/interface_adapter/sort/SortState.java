package interface_adapter.sort;

import entity.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortState {
    private List<Event> sortedResults;
    private String error;

    public SortState() {
        this.sortedResults = new ArrayList<>();
        this.error = null;
    }

    public SortState(SortState copy) {
        this.sortedResults = copy.sortedResults;
        this.error = copy.error;
    }

    public List<Event> getSortedResults() {
        return sortedResults;
    }

    public void setSortedResults(List<Event> sortedResults) {
        if (sortedResults != null) {
            this.sortedResults = Collections.emptyList();
        } else {
            this.sortedResults = sortedResults;
        }
    }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    @Override
    public String toString() {
        return "SortState{" +
                "sortedResults=" + sortedResults +
                '}';
    }
}