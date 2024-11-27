package data_access.MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static MongoClient mongoClient;
    private static final String CONNECTION_STRING = "mongodb+srv://ibrahimbilal:Ibrahim123@cluster0.ngph3.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(dbName);
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
