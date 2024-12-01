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

    /**
     * Creates a new {@link Event} with the specified details.
     * <p>
     * This method validates that the event's start time is before its end time. If the start time
     * is after the end time, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param name the name of the event.
     * @param description a description of the event.
     * @param location the location where the event will take place.
     * @param start the start time of the event.
     * @param end the end time of the event.
     * @param tags a list of tags associated with the event.
     *
     * @return a newly created {@link Event} object with the provided details.
     *
     * @throws IllegalArgumentException if the start time is after the end time.
     */
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
