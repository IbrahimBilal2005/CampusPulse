package use_case.signup.event_poster_signup;

import data_access.InMemoryUserDataAccessObject;
import entity.EventPosterCreationStrategy;
import entity.UserCreationStrategy;
import org.junit.jupiter.api.Test;
import entity.Account;
import entity.AccountCreationStrategy;
import use_case.signup.UserSignupDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class EventPosterSignupInteractorTest {

    @Test
    void successTest() {
        EventPosterSignupInputData inputData = new EventPosterSignupInputData("username", "password", "password", "Organization Name", "sopLink");
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
        EventPosterSignupInteractor interactor = new EventPosterSignupInteractor(userRepository, successPresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        EventPosterSignupInputData inputData = new EventPosterSignupInputData("username", "password", "password", "Organization Name", "sopLink");
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        EventPosterSignupOutputBoundary failurePresenter = new EventPosterSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(EventPosterSignupOutputData eventPoster) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords don't match.", errorMessage);
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
        EventPosterSignupInteractor interactor = new EventPosterSignupInteractor(userRepository, failurePresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {

    }

}
