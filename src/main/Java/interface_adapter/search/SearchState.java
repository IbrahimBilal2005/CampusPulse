package interface_adapter.search;

import entity.Event;
import java.util.List;

public class SearchState {
    private List<Event> results;
    private String error;

    public SearchState(List<Event> results, String error) {
        this.results = results;
        this.error = error;
    }

    public List<Event> getResults() {
        return results;
    }

    public String getError() {
        return error;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }

    public void setError(String error) {
        this.error = error;
    }
}
