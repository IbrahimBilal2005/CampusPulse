package use_case.signup.general_user_signup;

import data_access.InMemoryUserDataAccessObject;
import org.junit.jupiter.api.Test;
import entity.Account;
import entity.UserCreationStrategy;
import entity.AccountCreationStrategy;
import use_case.signup.UserSignupDataAccessInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserSignupInteractorTest {

    @Test
    void successTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupOutputBoundary successPresenter = new UserSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(UserSignupOutputData user) {
                assertEquals("username", user.getUsername());
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
        UserSignupInputBoundary interactor = new UserSignupInteractor(userRepository, successPresenter, new UserCreationStrategy());
        interactor.execute(inputData);
    }
    @Test
    void failurePasswordMismatchTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "wrong", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupOutputBoundary failurePresenter = new UserSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(UserSignupOutputData user) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords do not match", errorMessage);
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
        UserSignupInputBoundary interactor = new UserSignupInteractor(userRepository, failurePresenter, new UserCreationStrategy());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        AccountCreationStrategy accountCreator = new UserCreationStrategy();
        Account user = accountCreator.createAccount("username", "password", "firstName", "lastName", 18, "gender", List.of("art"));
        userRepository.save(user);

        UserSignupOutputBoundary failurePresenter = new UserSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(UserSignupOutputData user) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("User already exists", errorMessage);
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
        UserSignupInputBoundary interactor = new UserSignupInteractor(userRepository, failurePresenter, new UserCreationStrategy());
        interactor.execute(inputData);
    }
}
