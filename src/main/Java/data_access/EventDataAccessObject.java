package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.*;
import org.bson.Document;
import use_case.filter.FilterDataAccessInterface;
import use_case.search.SearchDataAccessInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return List.of();
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
        return List.of();
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
