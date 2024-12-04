package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.*;
import org.bson.Document;
import use_case.filter.FilterDataAccessInterface;
import use_case.search.SearchDataAccessInterface;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventDataAccessObject implements SearchDataAccessInterface,
                                              FilterDataAccessInterface {

    private readDBInterface mongoConnection = new MongoConnection();

    private final MongoCollection<Document> eventsCollection;
    private final EventCreationStrategy eventCreationStrategy;
    private Map<String, Event> events = new HashMap<>();

    public EventDataAccessObject(EventCreationStrategy eventCreationStrategy,
                                 Map<String, Event> events,
                                 readDBInterface mongoConnection) {

        this.eventCreationStrategy = eventCreationStrategy;
        this.events = events;
        this.mongoConnection = mongoConnection;
        this.eventsCollection = mongoConnection.getEventsCollection();

        loadEvents();
    }

    private void loadEvents() {
        try (MongoCursor<Document> cursor = eventsCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String name = doc.getString("name");
                String description = doc.getString("description");
                String location = doc.getString("location");
                LocalDateTime start = LocalDateTime.parse(doc.getString("start"), DateTimeFormatter.ISO_DATE_TIME);
                LocalDateTime end = LocalDateTime.parse(doc.getString("end"), DateTimeFormatter.ISO_DATE_TIME);
                List<String> tags = (List<String>) doc.get("tags");

                Event event = eventCreationStrategy.createEvent(name, description, location, start, end, tags);
                events.put(event.getName(), event);  // Add event to accounts map
            }
        }
    }

    /**
     * Save the event to the db
     * @param event the account to save
     */
    // @Override
    private void save(Event event) {
        Document accountDoc = new Document("name", event.getName())
                .append("description", event.getDescription())
                .append("location", event.getLocation())
                .append("start", event.getStart().format(DateTimeFormatter.ISO_DATE_TIME))
                .append("end", event.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))
                .append("tags", event.getTags());

            eventsCollection.insertOne(accountDoc);
    }

    /**
     * Add an event
     * @param event the event to add
     */
    void addEvent(Event event) {
        events.put(event.getName(), event);
        save(event);
    }

    /**
     * Remove an event
     * @param event the event to add
     */
    void deleteEvent(Event event) {
        events.remove(event.getName());

        // Create a filter to find the event by its name
        Document filter = new Document("name", event.getName());

        // Delete the event from the MongoDB collection
        eventsCollection.deleteOne(filter);
    }

    /**
     * Searches for events that match the specified criteria.
     *
     * @param query the phrase searched by the user.
     * @return a list of events matching the search criteria.
     */
    @Override
    public List<Event> searchEvents(String query) {
        String lowerCaseQuery = query.toLowerCase();

        return events.values().stream()
                .filter(event ->
                        // Match event name using Levenshtein Distance or Cosine Similarity
                        EventDAO.Levenshtein.isSimilar(event.getName(), lowerCaseQuery, 3) ||
                                EventDAO.CosineSimilarity.isSimilar(event.getName(), lowerCaseQuery, 0.7) ||
                                // Match event tags using Levenshtein or Cosine Similarity
                                event.getTags().stream().anyMatch(tag ->
                                        EventDAO.Levenshtein.isSimilar(tag, lowerCaseQuery, 3) ||
                                                EventDAO.CosineSimilarity.isSimilar(tag, lowerCaseQuery, 0.7)
                                )
                )
                .collect(Collectors.toList());
    }

    /**
     * Searches for events that match the specified criteria.
     *
     * @param filterCriteria the phrase searched by the user.
     * @param events
     * @return a list of events matching the search criteria.
     */
    @Override
    public List<Event> filterEvents(Map<String, Object> filterCriteria, List<Event> events) {
        // Safely retrieve the duration and handle null values
        Integer duration = (Integer) filterCriteria.getOrDefault("duration", 0);

        // Retrieve other filters
        String location = (String) filterCriteria.get("location");
        List<String> tags = (List<String>) filterCriteria.getOrDefault("tags", Collections.emptyList());

        // If no filters are selected, return all events
        if (duration == 0 && location == null && tags.isEmpty()) {
            return events; // Assuming `events` contains all stored events
        }

        // Apply filters
        return events.stream()
                .filter(event -> {
                    // Filter by duration if specified
                    boolean matchesDuration = true;
                    if (duration != 0) {
                        if (event.getStart() == null || event.getEnd() == null) {
                            matchesDuration = false;
                        } else {
                            long eventDuration = Duration.between(event.getStart(), event.getEnd()).toHours();
                            matchesDuration = duration == eventDuration;
                        }
                    }

                    // Filter by location if specified
                    boolean matchesLocation = location == null || event.getLocation().toLowerCase().contains(location.toLowerCase());

                    // Filter by tags if specified
                    boolean matchesTags = tags.isEmpty() || event.getTags().stream().anyMatch(tags::contains);

                    // Return true if all filters match
                    return matchesDuration && matchesLocation && matchesTags;
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all events in the system (optional, depending on your use case).
     *
     * @return a list of all events.
     */
    @Override
    public List<Event> getAllEvents() {
        return List.of();
    }
}
