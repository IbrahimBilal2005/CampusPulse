package entity;

import java.time.LocalDateTime;
import java.util.List;

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
