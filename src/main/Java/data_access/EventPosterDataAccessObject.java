package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.Account;
import entity.*;
import org.bson.Document;
import use_case.signup.event_poster_signup.EventPosterSignupDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class EventPosterDataAccessObject implements EventPosterSignupDataAccessInterface {

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
}
