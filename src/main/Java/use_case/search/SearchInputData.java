package use_case.search;

public class SearchInputData {
    private final String category;

    public SearchInputData(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
