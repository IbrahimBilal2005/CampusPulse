package use_case.loginTest;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {
    private InMemoryUserDataAccessObject mockUserDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor interactor;

    private String failMessage;
    private boolean successCalled;

    @BeforeEach
    void setUp() {
        // Manually implement the mock-like dependencies
        mockUserDataAccess = new InMemoryUserDataAccessObject();
        mockUserDataAccess.addAccount(new User("Tanay06", "Pulselife", "Tanay", "Langhe", 18, "Male", null));

        mockPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                if (!outputData.isUseCaseFailed()) {
                    successCalled = true;
                }
            }

            @Override
            public void prepareFailView(String errorMessage) {
                failMessage = errorMessage;
            }
        };

        interactor = new LoginInteractor(mockUserDataAccess, mockPresenter);
        failMessage = null;
        successCalled = false;
    }

    @Test
    void testExecute_UsernameDoesNotExist() {
        // Arrange
        LoginInputData inputData = new LoginInputData("nonexistentUser", "password");

        // Act
        interactor.execute(inputData);

        // Assert
        assertNotNull(failMessage);
        assertEquals("Account nonexistentUser does not exist.", failMessage);
        assertFalse(successCalled);
    }

    @Test
    void testExecute_IncorrectPassword() {
        // Arrange
        LoginInputData inputData = new LoginInputData("Tanay06", "IncorrectPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertNotNull(failMessage);
        assertEquals("Incorrect password for \"Tanay06\".", failMessage);
        assertFalse(successCalled);
    }

    @Test
    void testExecute_CorrectCredentials() {
        // Arrange
        LoginInputData inputData = new LoginInputData("Tanay06", "Pulselife");

        // Act
        interactor.execute(inputData);

        // Assert
        assertNull(failMessage);
        assertTrue(successCalled);
    }
}
