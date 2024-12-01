package use_case.delete_event;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

class DeleteEventInteractorTest {

    @Test
    void successTest() {
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("Education Seminar", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put(event1.getName(), event1);
        eventMap.put(event2.getName(), event2);

        Map<String, Event> expectedMap = new HashMap<>();
        expectedMap.put(event1.getName(), event1);

        DeleteEventInputData deleteEventInputData = new DeleteEventInputData("username", event2);
        DeleteEventDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        AccountCreationStrategy accountCreator = new EventPosterCreationStrategy();
        Account eventPoster = accountCreator.createAccount("username", "password123", "TechCorp", "http://sop.link", eventMap);
        userRepository.addAccount((EventPoster) eventPoster);

        DeleteEventOutputBoundary successPresenter = new DeleteEventOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteEventOutputData outputData) {
                assertEquals(expectedMap, ((EventPoster) eventPoster).getEvents());
                assertTrue(userRepository.getUser(eventPoster.getUsername()).getEvents().equals(expectedMap));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }
        };
        DeleteEventInputBoundary interactor = new DeleteEventInteractor(userRepository, successPresenter, new EventPosterCreationStrategy());
        interactor.deleteEvent(deleteEventInputData);
    }

    @Test
    void failureTest() {
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("DOES NOT EXIST", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put(event1.getName(), event1);

        DeleteEventInputData deleteEventInputData = new DeleteEventInputData("username", event2);
        DeleteEventDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        AccountCreationStrategy accountCreator = new EventPosterCreationStrategy();
        Account eventPoster = accountCreator.createAccount("username", "password123", "TechCorp", "http://sop.link", eventMap);
        userRepository.addAccount((EventPoster) eventPoster);

        DeleteEventOutputBoundary failurePresenter = new DeleteEventOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteEventOutputData outputData) {
                fail("Success not expected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Event does not exist.", errorMessage);
            }
        };
        DeleteEventInputBoundary interactor = new DeleteEventInteractor(userRepository, failurePresenter, new EventPosterCreationStrategy());
        interactor.deleteEvent(deleteEventInputData);
    }

}
