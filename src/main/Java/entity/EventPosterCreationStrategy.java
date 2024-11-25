package entity;
import java.util.List;
import java.util.Map;

// Strategy for creating an EventPoster
public class EventPosterCreationStrategy implements AccountCreationStrategy {

    @Override
    public Account createAccount(String username, String password, Object... params) {
        // Assuming params are [organizationName, sopLink, tags, events]
        String organizationName = (String) params[0];
        String sopLink = (String) params[1];
        List<String> tags = (List<String>) params[2];
        Map<Integer, Event> events = (Map<Integer, Event>) params[3]; // Map<Integer, Event>

        return new EventPoster(username, password, organizationName, sopLink, tags, events);
    }
}
