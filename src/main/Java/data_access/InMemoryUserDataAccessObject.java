package data_access;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Account;
import entity.Event;
import entity.EventPoster;
import use_case.admin_account_approval.AdminApprovalUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.delete_event.DeleteEventDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.UserSignupDataAccessInterface;
import use_case.admin_account_approval.AdminApprovalUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements UserSignupDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        DeleteEventDataAccessInterface,
        AdminApprovalUserDataAccessInterface {

    private final Map<String, Account> users = new HashMap<>();

    public InMemoryUserDataAccessObject() {
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("Education Seminar", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put(event1.getName(), event1);
        eventMap.put(event2.getName(), event2);

        EventPoster eventPoster = new EventPoster("john_doe", "password123", "TechCorp", "http://sop.link", eventMap);
        users.put(eventPoster.getUsername(), eventPoster);
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean nameExists(String username) {
        return false;
    }

    @Override
    public void save(Account user) {
        users.put(user.getUsername(), user);}

    @Override
    public Account get(String username) {
        return null;
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public Account getAccountByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(Account updatedAccount, String newPassword) {
        if (!users.containsKey(updatedAccount.getUsername())) {
            throw new IllegalArgumentException("Account not found."); // Handle non-existing accounts
        }

        Account oldAccount = users.get(updatedAccount.getUsername());
        oldAccount.setPassword(newPassword);
    }

    /**
     * Adds a test account to the in-memory store.
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        users.put(account.getUsername(), account);
    }

    @Override
    public EventPoster getUser(String username) {
        return (EventPoster) users.get(username);
    }

    @Override
    public void deleteEvent(EventPoster eventPoster, Event eventToDelete) {
        EventPoster eventPosterToDelete = (EventPoster) users.get(eventPoster.getUsername());
        eventPosterToDelete.getEvents().remove(eventToDelete.getName());
        users.put(eventPoster.getUsername(), eventPosterToDelete);

    }

    @Override
    public void addAccount(EventPoster eventPoster) {
        users.put(eventPoster.getUsername(), eventPoster);
    }

    @Override
    public boolean eventExists(String username, Event event) {
        EventPoster eventPoster = (EventPoster) users.get(username);
        return eventPoster.getEvents().containsKey(event.getName());
    }

    @Override
    public boolean approveUserAsEventPoster(String uid) {
        EventPoster eventPoster = (EventPoster) users.get(uid);
        eventPoster.setApproved(true);
        users.put(uid, eventPoster);
        return true;
    }

    @Override
    public boolean rejectUserAsEventPoster(String uid) {
        EventPoster eventPoster = (EventPoster) users.get(uid);
        eventPoster.setApproved(false);
        users.put(uid, eventPoster);
        return true;

    }

}

