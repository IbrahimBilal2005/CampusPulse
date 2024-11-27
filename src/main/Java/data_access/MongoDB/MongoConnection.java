package data_access.MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoConnection implements readDBInterface{

    // instead of hard coding use files and getDB service to abstract
    private MongoClient mongoClient = MongoClients.create("mongodb+srv://ibrahimbilal:Ibrahim123@cluster0.ngph3.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
    private MongoDatabase database = mongoClient.getDatabase("CampusPulse");

    private MongoCollection<Document> eventsCollection = database.getCollection("events");
    private MongoCollection<Document> eventPostersCollection = database.getCollection("eventPosters");
    private MongoCollection<Document> usersCollection = database.getCollection("users");
    private MongoCollection<Document> adminsCollection = database.getCollection("admins");

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
