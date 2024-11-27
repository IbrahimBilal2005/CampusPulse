package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.MongoDB.MongoConnection;
import data_access.MongoDB.readDBInterface;
import entity.Account;
import entity.AccountCreationStrategy;
import entity.UserCreationStrategy;
import org.bson.Document;
import use_case.signup.UserSignupDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataAccessObject implements UserSignupDataAccessInterface {

    private readDBInterface mongoConnection = new MongoConnection();
    private MongoCollection<Document> UsersCollection;
    private Map<String, Account> accounts = new HashMap<>();
    private AccountCreationStrategy accountCreationStrategy = new UserCreationStrategy();

    public UserDataAccessObject(AccountCreationStrategy accountCreationStrategy,
                                Map<String, Account> accounts,
                                readDBInterface mongoConnection) {
        this.accountCreationStrategy = accountCreationStrategy;
        this.accounts = accounts;
        this.mongoConnection = mongoConnection;
        this.UsersCollection = mongoConnection.getUsersCollection();

        try (MongoCursor<Document> cursor = UsersCollection.find().iterator()) {
            while (cursor.hasNext()) {

                Document doc = cursor.next();
                String username = doc.getString("username");
                String password = doc.getString("password");
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                Integer age = doc.getInteger("age");
                String gender = doc.getString("gender");
                List<String> interests = (List<String>) doc.get("interests");

                Account user = accountCreationStrategy.createAccount(username, password, firstName, lastName, age, gender, interests);
                accounts.put(username, user);
            }
        }
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void save(Account user) {

    }
}
