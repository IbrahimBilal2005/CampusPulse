package use_case.search;

public class SearchInputData {
    private String query;  // The search term entered by the user (event name or keyword)

    // Constructor
    public SearchInputData(String query) {
        this.query = query;
    }

    // Getter for query (search term)
    public String getQuery() {
        return query;
    }
}
