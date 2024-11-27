package data_access.readDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static data_access.readDB.GetDB.*;


public class MongoConnection implements readDBInterface{

    // instead of hard coding use files and getDB service to abstract
    private MongoClient mongoClient = MongoClients.create(getURI());
    private MongoDatabase database = mongoClient.getDatabase(getDBName());

    private MongoCollection<Document> eventsCollection = database.getCollection(GetDB.getEventsCollection());
    private MongoCollection<Document> eventPostersCollection = database.getCollection(GetDB.getEventPostersCollection());
    private MongoCollection<Document> usersCollection = database.getCollection(GetDB.getUsersCollection());
    private MongoCollection<Document> adminsCollection = database.getCollection(getAdminsCollection());

    @Override
    public MongoCollection<Document> getEventsCollection() {
        return eventsCollection;
    }

    @Override
    public MongoCollection<Document> getUsersCollection() {
        return usersCollection;
    }

    @Override
    public MongoCollection<Document> getEventPostersCollection() {
        return eventPostersCollection;
    }

    @Override
    public MongoCollection<Document> getAdminCollection() {
        return adminsCollection;
    }
}
