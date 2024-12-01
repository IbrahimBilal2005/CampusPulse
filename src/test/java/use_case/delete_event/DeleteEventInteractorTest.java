package use_case.delete_event;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        DeleteEventInputBoundary interactor = getDeleteEventInputBoundary(expectedMap, eventPoster, userRepository);
        interactor.deleteEvent(deleteEventInputData);
    }

    @NotNull
    private static DeleteEventInputBoundary getDeleteEventInputBoundary(Map<String, Event> expectedMap, Account eventPoster, DeleteEventDataAccessInterface userRepository) {
        DeleteEventOutputBoundary successPresenter = new DeleteEventOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteEventOutputData outputData) {
                Assertions.assertEquals(expectedMap, ((EventPoster) eventPoster).getEvents());
                Assertions.assertEquals(userRepository.getUser(eventPoster.getUsername()).getEvents(), expectedMap);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("Use case failure is unexpected");
            }
        };
        return new DeleteEventInteractor(userRepository, successPresenter);
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

        DeleteEventInputBoundary interactor = getDeleteEventInputBoundary(userRepository);
        interactor.deleteEvent(deleteEventInputData);
    }

    @NotNull
    private static DeleteEventInputBoundary getDeleteEventInputBoundary(DeleteEventDataAccessInterface userRepository) {
        DeleteEventOutputBoundary failurePresenter = new DeleteEventOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteEventOutputData outputData) {
                Assertions.fail("Success not expected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Event does not exist.", errorMessage);
            }
        };
        return new DeleteEventInteractor(userRepository, failurePresenter);
    }

}
