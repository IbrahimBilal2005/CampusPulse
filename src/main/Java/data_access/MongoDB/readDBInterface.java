package data_access.MongoDB;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface readDBInterface {
    public MongoCollection<Document> getEventsCollection();
    public MongoCollection<Document> getUsersCollection();
    public MongoCollection<Document> getEventPostersCollection();
    public MongoCollection<Document> getAdminCollection();
}