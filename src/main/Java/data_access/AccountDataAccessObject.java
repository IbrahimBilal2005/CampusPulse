package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import data_access.readDB.readDBInterface;
import entity.Account;
import entity.AccountCreationStrategy;
import entity.Event;
import entity.EventPoster;
import entity.User;
import org.bson.Document;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.delete_event.DeleteEventDataAccessInterface;
import use_case.new_event_post.NewEventPostUserDataAccessInterface;
import use_case.signup.AccountSignupDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDataAccessObject implements DeleteEventDataAccessInterface,
                                                ChangePasswordUserDataAccessInterface,
                                                AccountSignupDataAccessInterface,
                                                NewEventPostUserDataAccessInterface {
    private final EventDataAccessObject eventDataAccessObject;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private readDBInterface mongoConnection = new MongoConnection();

    private final MongoCollection<Document> eventPosterCollection;
    private final MongoCollection<Document> userCollection;
    private final MongoCollection<Document> adminCollection;

    private Map<String, Account> accounts = new HashMap<>();

    private final AccountCreationStrategy eventPosterCreationStrategy;
    private final AccountCreationStrategy userCreationStrategy;
    private final AccountCreationStrategy adminCreationStrategy;


    public AccountDataAccessObject(AccountCreationStrategy eventPosterCreationStrategy,
                                   AccountCreationStrategy userCreationStrategy,
                                   AccountCreationStrategy adminCreationStrategy,
                                   Map<String, Account> accounts,
                                   readDBInterface mongoConnection,
                                   EventDataAccessObject eventDataAccessObject) {

        this.eventPosterCreationStrategy = eventPosterCreationStrategy;
        this.userCreationStrategy = userCreationStrategy;
        this.adminCreationStrategy = adminCreationStrategy;
        this.eventDataAccessObject = eventDataAccessObject;

        this.accounts = accounts;
        this.mongoConnection = mongoConnection;

        this.eventPosterCollection = mongoConnection.getEventPostersCollection();
        this.userCollection = mongoConnection.getUsersCollection();
        this.adminCollection = mongoConnection.getAdminCollection();

        loadEventPosters();
        loadUsers();
    }

    /**
     * Load EventPosters from MongoDB and add them to the accounts map
     */
    private void loadEventPosters() {
        try (MongoCursor<Document> cursor = eventPosterCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
            String username = doc.getString(USERNAME);
                String password = doc.getString(PASSWORD);
                String organizationName = doc.getString("organizationName");
                String sopLink = doc.getString("sopLink");
                Map<String, Event> events = (Map<String, Event>) doc.get("events");

                EventPoster eventPoster = (EventPoster) eventPosterCreationStrategy.createAccount(username, password, organizationName, sopLink, events);
                accounts.put(username, eventPoster);  // Add EventPoster to accounts map
            }
        }
    }

    /**
     * Load Users from MongoDB and add them to the accounts map
     */
    private void loadUsers() {
        try (MongoCursor<Document> cursor = userCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String username = doc.getString(USERNAME);
                String password = doc.getString(PASSWORD);
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                Integer age = doc.getInteger("age");
                String gender = doc.getString("gender");
                List<String> interests = (List<String>) doc.get("interests");

                User user = (User) userCreationStrategy.createAccount(username, password, firstName, lastName, age, gender, interests);
                accounts.put(username, user);  // Add User to accounts map
            }
        }
    }

    /**
     * Save the account
     * @param account the account to save
     */
    @Override
    public void save(Account account) {
        Document accountDoc = new Document(USERNAME, account.getUsername())
                .append(PASSWORD, account.getPassword());

        if (account instanceof EventPoster) {
            EventPoster eventPoster = (EventPoster) account;
            accountDoc.append("organizationName", eventPoster.getOrganizationName())
                    .append("sopLink", eventPoster.getSopLink())
                    .append("events", eventPoster.getEvents());

            // Save to EventPosters collection
            eventPosterCollection.insertOne(accountDoc);
        } else if (account instanceof User) {
            User user = (User) account;
            accountDoc.append("firstName", user.getFirstName())
                    .append("lastName", user.getLastName())
                    .append("age", user.getAge())
                    .append("gender", user.getGender())
                    .append("interests", user.getInterests());

            // Save to Users collection
            userCollection.insertOne(accountDoc);
        }
    }

    /**
     * Delete the event from the given event poster's events field
     */
    @Override
    public void deleteEvent(EventPoster eventPoster, Event eventToDelete) {
        // Retrieve the EventPoster from the accounts map
        EventPoster eventPosterForDelete = (EventPoster) accounts.get(eventPoster.getUsername());

        if (eventPosterForDelete != null && eventPosterForDelete.getEvents().containsKey(eventToDelete.getName())) {
            // Remove the event from the EventPoster's events
            eventPosterForDelete.getEvents().remove(eventToDelete.getName());

            // Save the updated EventPoster to the map and the database
            accounts.put(eventPoster.getUsername(), eventPosterForDelete);
            save(eventPosterForDelete);  // Save the updated EventPoster to the database

            //delete the event from the events collection in the eventDAO
            eventDataAccessObject.deleteEvent(eventToDelete);
        }
    }

    /**
     * Check if the event exists in the given event poster's events field
     * @param username the event poster
     * @param event the vent to check
     * @return True if the event exists in the event poster's events field. False otherwise
     */
    @Override
    public boolean eventExists(String username, Event event) {
        Account account = accounts.get(username);
        if (account instanceof EventPoster) {
            EventPoster eventPoster = (EventPoster) account;
            // Check if the event exists in the EventPoster's events map
            return eventPoster.getEvents().containsKey(event.getName());
        }
        return false;  // Return false if the account is not an EventPoster or event doesn't exist
    }

    /**
     * Add the given event poster to the db
     * @param eventPoster the account to add
     */
    @Override
    public void addAccount(EventPoster eventPoster) {
        // Add the EventPoster to the accounts map.
        accounts.put(eventPoster.getUsername(), eventPoster);
        save(eventPoster);
    }

    /**
     * Use the username to check if the associated account exists
     * @param username the username to look for
     * @return True if the username is associated with an account. False otherwise.
     */
    @Override
    public boolean existsByName(String username) {
        // Return true if the account exists in the map.
        return accounts.containsKey(username);
    }

    /**
     * Adds event to given username
     *
     * @param event the event to add
     * @param username the eventPoster who created the event
     */
    @Override
    public void addtoMyevents(Event event, String username) {
        // find the associated event poster
        EventPoster eventPoster = (EventPoster) accounts.get(username);

        //update the event posters events
        eventPoster.getEvents().put(event.getName(), event);

        //save the event poster to the database
        save(eventPoster);

        //add the event to the events database
        eventDataAccessObject.addEvent(event);
    }

    /**
     *
     * @param username the username to check if the associated account exists
     * @return the account associated with the given username
     */
    @Override
    public Account getAccountByUsername(String username) {
        return accounts.get(username);
    }

    /**
     * Change the password for the given account to the given password
     * @param account the account whose password to change
     * @param newPassword the new password
     */
    @Override
    public void changePassword(Account account, String newPassword) {
        account.setPassword(newPassword);
        accounts.put(account.getUsername(), account);
        save(account);  // This will ensure the password change is reflected in the database as well
    }
}
