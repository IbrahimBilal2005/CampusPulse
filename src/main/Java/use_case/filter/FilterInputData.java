package use_case.filter;

import java.time.LocalDateTime;
import java.util.List;

public class FilterInputData {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final List<String> tags;
    private final String location;

    public FilterInputData(LocalDateTime startTime, LocalDateTime endTime, List<String> tags, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getLocation() {
        return location;
    }
}