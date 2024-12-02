package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A strategy interface for creating {@link Event} objects.
 * <p>
 * Implementations of this interface encapsulate the logic required to create different types
 * of events. This allows flexibility and scalability when managing various event creation processes.
 * </p>
 */
public interface EventCreationStrategy {
    Event createEvent(String name,
                      String description,
                      String location,
                      LocalDateTime start,
                      LocalDateTime end,
                      List<String> tags);
}
