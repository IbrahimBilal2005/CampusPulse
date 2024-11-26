package use_case.signup.event_poster_signup;

import data_access.InMemoryUserDataAccessObject;
import entity.AccountCreationStrategy;
import entity.Event;
import entity.EventPosterCreationStrategy;
import entity.Account;
import org.junit.jupiter.api.Test;
import use_case.signup.UserSignupDataAccessInterface;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventPosterSignupInteractorTest {

    @Test
    void successTest() {
        EventPosterSignupInputData inputData = new EventPosterSignupInputData("username", "password", "password", "Organization Name", "sopLink", new HashMap<String, Event>() {{
            put("Event1", new Event("Name", "Description", "location", LocalDateTime.now(), LocalDateTime.now(), List.of("tag1", "tag2")));
        }} );

        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        EventPosterSignupOutputBoundary successPresenter = new EventPosterSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(EventPosterSignupOutputData eventPoster) {
                assertEquals("username", eventPoster.getUsername());
                assertTrue(userRepository.existsByName("username"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                //expected
            }

            @Override
            public void switchToBaseView() {
                //expected
            }
        };
        EventPosterSignupInputBoundary interactor = new EventPosterSignupInteractor(userRepository, successPresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        EventPosterSignupInputData inputData = new EventPosterSignupInputData("username", "password", "wrong", "Organization Name", "sopLink", new HashMap<String, Event>() {{
            put("Event1", new Event("Name", "Description", "location", LocalDateTime.now(), LocalDateTime.now(), List.of("tag1", "tag2")));
        }} );

        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        EventPosterSignupOutputBoundary failurePresenter = new EventPosterSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(EventPosterSignupOutputData eventPoster) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords do not match.", errorMessage);
            }

            @Override
            public void switchToLoginView() {
                //expected
            }

            @Override
            public void switchToBaseView() {
                //expected
            }
        };
        EventPosterSignupInputBoundary interactor = new EventPosterSignupInteractor(userRepository, failurePresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        EventPosterSignupInputData inputData = new EventPosterSignupInputData("username", "password", "password", "Organization Name", "sopLink", new HashMap<String, Event>() {{
            put("Event1", new Event("Name", "Description", "location", LocalDateTime.now(), LocalDateTime.now(), List.of("tag1", "tag2")));
        }} );

        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        AccountCreationStrategy accountCreator = new EventPosterCreationStrategy();
        Account user = accountCreator.createAccount("username", "password", "Organization Name", "sopLink", new HashMap<String, Event>() {{
            put("Event1", new Event("Name", "Description", "location", LocalDateTime.now(), LocalDateTime.now(), List.of("tag1", "tag2")));
        }} );

        userRepository.save(user);

        EventPosterSignupOutputBoundary failurePresenter = new EventPosterSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(EventPosterSignupOutputData eventPoster) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("User already exists.", errorMessage);
            }

            @Override
            public void switchToLoginView() {
                //expected
            }

            @Override
            public void switchToBaseView() {
                //expected
            }
        };
        EventPosterSignupInputBoundary interactor = new EventPosterSignupInteractor(userRepository, failurePresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }
}
