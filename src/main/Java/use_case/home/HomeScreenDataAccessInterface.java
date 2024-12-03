package use_case.home;

import entity.Event;
import entity.User;
import java.util.List;

public interface HomeScreenDataAccessInterface {
    List<Event> fetchEvents(User user); // Fetch events relevant to the user
}
