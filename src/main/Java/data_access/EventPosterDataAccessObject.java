package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.Account;
import entity.*;
import org.bson.Document;
import use_case.delete_event.DeleteEventDataAccessInterface;
import use_case.signup.AccountSignupDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * The Data Access Object for Event Poster related data
 */
public class EventPosterDataAccessObject implements AccountSignupDataAccessInterface, DeleteEventDataAccessInterface {

    private readDBInterface mongoConnection = new MongoConnection();
    private MongoCollection<Document> EventPostersCollection;
    private Map<String, EventPoster> eventPosters = new HashMap<>();
    private AccountCreationStrategy accountCreationStrategy;

    public EventPosterDataAccessObject(AccountCreationStrategy accountCreationStrategy,
                                       Map<String, EventPoster> eventPosters,
                                       readDBInterface mongoConnection) {

        this.accountCreationStrategy = accountCreationStrategy;
        this.eventPosters = eventPosters;
        this.mongoConnection = mongoConnection;
        this.EventPostersCollection = mongoConnection.getEventPostersCollection();

        try (MongoCursor<Document> cursor = EventPostersCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String username = doc.getString("username");
                String password = doc.getString("password");
                String organizationName = doc.getString("organizationName");
                String sopLink = doc.getString("sopLink");
                Map<String, Event> events = (Map<String, Event>) doc.get("events");

                EventPoster eventPoster = (EventPoster) accountCreationStrategy.createAccount(username, password, organizationName, sopLink, events);
                eventPosters.put(username, eventPoster);
            }
        }
    }

    /**
     * Check if the event poster exists
     * @param username the username to look for
     * @return True of False depending on if the event poster exists
     */
    @Override
    public boolean existsByName(String username) {
        return eventPosters.containsKey(username);
    }

    @Override
    public void save(Account account) {
        EventPoster eventPoster = (EventPoster) account;
        Document doc = new Document();
        doc.append("username", eventPoster.getUsername());
        doc.append("password", eventPoster.getPassword());
        doc.append("organizationName", eventPoster.getOrganizationName());
        doc.append("sopLink", eventPoster.getSopLink());
        doc.append("events", eventPoster.getEvents());

        EventPostersCollection.insertOne(doc);
        eventPosters.put(eventPoster.getUsername(), eventPoster);
    }



    @Override
    public Account getAccountByUsername(String username) {
        return null;
    }

    /**
     * Delete the given event from the given event posters events
     * @param eventPoster the event poster whose event to delete
     * @param eventToDelete the event to delete
     */
    @Override
    public void deleteEvent(EventPoster eventPoster, Event eventToDelete) {
        EventPoster eventPosterForDelete = eventPosters.get(eventPoster.getUsername());
        eventPosterForDelete.getEvents().remove(eventToDelete.getName());
        eventPosters.put(eventPoster.getUsername(), eventPosterForDelete);
    }

    /**
     * Check if the given event exists in the given event posters events
     * @param username the event poster
     * @param event the event to check
     * @return True if the event exists in the event posters events. False otherwise
     */
    @Override
    public boolean eventExists(String username, Event event) {

        EventPoster eventPoster = eventPosters.get(username);
        return eventPoster.getEvents().containsKey(event.getName());
    }

    /**
     * Add the eventPoster to the list of event Posters
     * @param eventPoster the event poster to add
     */
    @Override
    public void addAccount(EventPoster eventPoster) {
        eventPosters.put(eventPoster.getUsername(), eventPoster);
    }
}
