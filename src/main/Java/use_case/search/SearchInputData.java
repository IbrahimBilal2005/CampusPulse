package use_case.search;

import java.util.List;

public class SearchInputData {
    private final String category;
    private final List<String> tags;

    public SearchInputData(String category, List<String> tags) {
        this.category = category;
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getTags() {
        return tags;
    }
}
