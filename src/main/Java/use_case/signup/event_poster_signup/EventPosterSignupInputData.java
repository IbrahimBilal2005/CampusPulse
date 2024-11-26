package use_case.signup.event_poster_signup;

import entity.Event;

import java.util.HashMap;
import java.util.Map;

public class EventPosterSignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String organizationName;
    private final String sopLink;
    private final Map<String, Event> events;

    public EventPosterSignupInputData(String username,
                                      String password,
                                      String repeatPassword,
                                      String organizationName,
                                      String sopLink,
                                      Map<String, Event> events) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.organizationName = organizationName;
        this.sopLink = sopLink;
        this.events = events;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }
    String getRepeatPassword() { return repeatPassword; }
    String getOrganizationName() { return organizationName; }
    String getSopLink() { return sopLink; }
    Map<String, Event> getEvents() { return events; }

}
