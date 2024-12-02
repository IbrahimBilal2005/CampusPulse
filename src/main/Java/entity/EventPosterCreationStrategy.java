package entity;
import java.util.List;
import java.util.Map;

/**
 * A strategy for creating an {@link EventPoster} account.
 * <p>
 * This class implements the {@link AccountCreationStrategy} interface and provides the logic
 * to create an {@link EventPoster} object with the specified details such as organization name,
 * SOP link, and events.
 * </p>
 */
public class EventPosterCreationStrategy implements AccountCreationStrategy {

    /**
     * Creates a new {@link EventPoster} account with the specified details.
     * <p>
     * This method expects the following additional parameters:
     * <ul>
     *   <li>organizationName: The name of the organization the event poster represents.</li>
     *   <li>sopLink: The link to the Statement of Purpose (SOP) for the organization.</li>
     *   <li>events: A map of events the event poster is associated with, where the key is the event name
     *   and the value is the corresponding {@link Event} object.</li>
     * </ul>
     * </p>
     *
     * @param username the username for the {@link EventPoster} account.
     * @param password the password for the {@link EventPoster} account.
     * @param params additional parameters for creating the event poster account.
     *        These should include: organizationName (String), sopLink (String),
     *        and events (Map&lt;String, Event&gt;).
     *
     * @return a newly created {@link EventPoster} account with the provided details.
     */
    @Override
    public Account createAccount(String username, String password, Object... params) {
        // Assuming params are [organizationName, sopLink, tags, events]
        String organizationName = (String) params[0];
        String sopLink = (String) params[1];
        Map<String , Event> events = (Map<String, Event>) params[2]; // Map<Integer, Event>

        return new EventPoster(username, password, organizationName, sopLink, events);
    }
}
