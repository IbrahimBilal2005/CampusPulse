package interface_adapter.search;

import entity.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchState {
    private List<Event> results;
    private String error;

    public SearchState() {
        this.results = new ArrayList<>();
        this.error = null;
    }

    public SearchState(SearchState copy) {
        this.results = copy.results;
        this.error = copy.error;
    }

    public List<Event> getResults() {
        return results;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SearchState{" +
                "results=" + results +
                '}';
    }
}
