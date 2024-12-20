package use_case.signup.general_user_signup;

import data_access.InMemoryUserDataAccessObject;
import org.jetbrains.annotations.NotNull;
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
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("User case failure is unexpected.");
            }
        };
        UserSignupInputBoundary interactor = new UserSignupInteractor(userRepository, successPresenter, new UserCreationStrategy());
        interactor.execute(inputData);
    }
    @Test
    void failurePasswordMismatchTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "wrong", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("Passwords do not match", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidFirstNameTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "123", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("Invalid first name", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidLastNameTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "123", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("Invalid last name", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureShortPasswordTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "short", "short", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("Password must be at least 8 characters", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidGenderTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "123", "lastName", 18, "", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("No gender selected", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidInterestsTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "lastName", 8, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("No interests selected", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidAgeTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "lastName", 18, "gender", null);
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupInputBoundary interactor = getUserSignupInputBoundary("Age must be at least 18", userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "firstName", "lastName", 18, "gender", List.of("art"));
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        AccountCreationStrategy accountCreator = new UserCreationStrategy();
        Account user = accountCreator.createAccount("username", "password", "firstName", "lastName", 18, "gender", List.of("art"));
        userRepository.save(user);

        UserSignupInputBoundary interactor = getUserSignupInputBoundary("User already exists", userRepository);
        interactor.execute(inputData);
    }


    @NotNull
    private static UserSignupInputBoundary getUserSignupInputBoundary(String error, UserSignupDataAccessInterface userRepository) {
        UserSignupOutputBoundary failurePresenter = new UserSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(UserSignupOutputData user) {
                fail("User case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(error, errorMessage);
            }
        };
        return new UserSignupInteractor(userRepository, failurePresenter, new UserCreationStrategy());
    }
}
