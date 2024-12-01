package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A strategy for creating standard events.
 * <p>
 * This class implements the {@link EventCreationStrategy} interface and provides the logic
 * to create a standard {@link Event} object. It ensures that the event's start time
 * is before its end time.
 * </p>
 */
public class StandardEventCreationStrategy implements EventCreationStrategy {

    @Override
    public Event createEvent(String name,
                             String description,
                             String location,
                             LocalDateTime start,
                             LocalDateTime end,
                             List<String> tags) {
        // You can add validation or additional logic here if needed
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        return new Event(name, description, location, start, end, tags);
    }
}
