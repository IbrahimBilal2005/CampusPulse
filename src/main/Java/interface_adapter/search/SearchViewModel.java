package interface_adapter.search;

import entity.Event;

import java.util.List;

public class SearchViewModel {
    private List<Event> results;
    private String error;

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
}
