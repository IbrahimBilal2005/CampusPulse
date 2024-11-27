package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.User;
import org.bson.Document;
import entity.Account;
import entity.AccountCreationStrategy;
import use_case.signup.general_user_signup.UserSignupDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataAccessObject implements UserSignupDataAccessInterface {

    private readDBInterface mongoConnection = new MongoConnection();
    private MongoCollection<Document> UsersCollection;
    private Map<String, Account> accounts = new HashMap<>();
    private AccountCreationStrategy accountCreationStrategy;

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

    /**
     * Retrieves the map of User objects.
     *
     * @return the map of User objects.
     */

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * Check if an account exists by the username.
     * @param username the username to look for
     * @return true if the account exists, otherwise false
     */
    @Override
    public boolean existsByName(String username) {
        return accounts.containsKey(username);
    }

    /**
     * Save a User object to the database.
     * @param account the user to save
     */
    @Override
    public void save(Account account) {
        User user = (User) account;
        Document doc = new Document();
        doc.append("username", user.getUsername());
        doc.append("password", user.getPassword());
        doc.append("firstName", user.getFirstName());
        doc.append("lastName", user.getLastName());
        doc.append("age", user.getAge());
        doc.append("gender", user.getGender());
        doc.append("interests", user.getInterests());

        UsersCollection.insertOne(doc);
        accounts.put(user.getUsername(), user);
    }
}
