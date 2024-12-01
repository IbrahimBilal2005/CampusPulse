package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventCreationStrategy {
    Event createEvent(String name,
                      String description,
                      String location,
                      LocalDateTime start,
                      LocalDateTime end,
                      List<String> tags);
}
