package data_access;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entity.Account;
import entity.Event;
import entity.EventPoster;
import use_case.admin_account_approval.AdminApprovalUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.delete_event.DeleteEventDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.new_event_post.NewEventPostUserDataAccessInterface;
import use_case.signup.UserSignupDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */

public class InMemoryUserDataAccessObject implements UserSignupDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        DeleteEventDataAccessInterface,
        AdminApprovalUserDataAccessInterface, LogoutUserDataAccessInterface, NewEventPostUserDataAccessInterface {

    private final Map<String, Account> users = new HashMap<>();
    private String currentUserName;

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
    public void addtoMyevents(Event event, String username) {

    }

    @Override
    public boolean nameExists(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(Account user) {
        users.put(user.getUsername(), user);}

    @Override
    public Account get(String username) { return users.get(username);
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

    /**
     * Retrieves the EventPoster associated with the specified username.
     *
     * @param username      the username of the event poster.
     * @param eventToDelete
     * @return the EventPoster object associated with the username.
     */


    @Override
    public void deleteEvent(String username, Event eventToDelete) {
        EventPoster eventPosterToDelete = (EventPoster) users.get(username);
        eventPosterToDelete.getEvents().remove(eventToDelete.getName());
        users.put(username, eventPosterToDelete);

    }

    /**
     * Get event poster's events
     *
     * @param username the username of the EventPoster.
     * @return true if the event exists for the specified user, false otherwise.
     */
    @Override
    public Map<String, Event> getUserEvents(String username) {
        EventPoster eventPoster = (EventPoster) users.get(username);
        return eventPoster.getEvents();
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


    public boolean approveUserAsEventPoster(String uid) {
        EventPoster eventPoster = (EventPoster) users.get(uid);
        eventPoster.setApproved(true);
        users.put(uid, eventPoster);
        return true;
    }

    @Override
    public boolean setApproval(String uid, boolean approvalState) {
        if (!users.containsKey(uid)) {
            return false;
        }
        EventPoster eventPoster = (EventPoster) users.get(uid);
        eventPoster.setApproved(approvalState);
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


    @Override
    public List<EventPoster> getUnapprovedUsers() {
        return users.values().stream()
                .filter(user -> user instanceof EventPoster)
                .map(user -> (EventPoster) user)
                .filter(eventPoster -> !eventPoster.isApproved())
                .collect(Collectors.toList());
    }


    /**
     * Returns the username of the curren user of the application.
     *
     * @return the username of the current user
     */
    @Override
    public String getCurrentUsername() {
        return this.currentUserName;
    }

    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username
     */
    @Override
    public void setCurrentUsername(String username) {
        this.currentUserName = username;
    }
}

