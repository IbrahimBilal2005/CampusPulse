package use_case.logout;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.AccountCreationStrategy;
import entity.UserCreationStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void successTest() {
        LogoutInputData inputData = new LogoutInputData("Paul");
        LogoutUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the data access repository and set him as the current user.
        AccountCreationStrategy creationStrategy = new UserCreationStrategy();
        User user = (User) creationStrategy.createAccount(
                "Paul", "password", "FirstName", "LastName", 25, "Male", List.of("Sports", "Music")
        );
        userRepository.setCurrentUsername(user.getUsername());

        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData outputData) {
                assertEquals("Paul", outputData.getUsername());
                assertFalse(outputData.getUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

        // Ensure the user was logged out
        assertNull(userRepository.getCurrentUsername());
    }
}