package interface_adapter.sort;

import entity.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The state for the Sort View Model.
 */
public class SortState {
    private String sortQuery = "";
    private List<Event> sortedResults = new ArrayList<>();
    private String error;

    public String getSortQuery() { return sortQuery; }
    public void setSortQuery(String sortQuery) { this.sortQuery = sortQuery; }

    public List<Event> getSortedResult() { return sortedResults; }
    public void setSortedResult(List<Event> sortedResults) { this.sortedResults = sortedResults; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    @Override
    public String toString() {
        return "SortState{"
                + "sortQuery=" + sortQuery + '\''
                + "sortedResults=" + sortedResults + '\''
                + '}';
    }
}