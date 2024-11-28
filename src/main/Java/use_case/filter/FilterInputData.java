package use_case.filter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FilterInputData {
    private final Integer duration;
    private final List<String> tags;
    private final String location;
    private final String query;

    public FilterInputData(Integer duration, String location, List<String> tags, String query) {
        this.duration = duration;
        this.tags = tags;
        this.location = location;
        this.query = query;
    }

    public Integer getDuration() {
        return Objects.requireNonNullElse(this.duration, 0);
    }

    public List<String> getTags() {
        return Objects.requireNonNullElse(this.tags, Collections.emptyList());
    }

    public String getLocation() {
        return Objects.requireNonNullElse(this.location, "");
    }

    public String getQuery() {
        return this.query;
    }
}