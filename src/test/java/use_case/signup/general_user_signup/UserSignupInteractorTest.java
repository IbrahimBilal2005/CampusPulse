package use_case.signup.general_user_signup;

import data_access.InMemoryUserDataAccessObject;
import entity.EventPosterCreationStrategy;
import org.junit.jupiter.api.Test;
import entity.Account;
import entity.UserCreationStrategy;
import entity.AccountCreationStrategy;
import use_case.signup.UserSignupDataAccessInterface;
import use_case.signup.event_poster_signup.*;

import static org.junit.jupiter.api.Assertions.*;
//import data_access.DATA_ACCESS_FILE

public class UserSignupInteractorTest {

    @Test
    void successTest() {
        UserSignupInputData inputData = new UserSignupInputData("username", "password", "password", "gender", 18);
        UserSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserSignupOutputBoundary successPresenter = new UserSignupOutputBoundary() {

            @Override
            public void prepareSuccessView(UserSignupOutputData eventPoster) {
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
        UserSignupInputBoundary interactor = new UserSignupInteractor(userRepository, successPresenter, new EventPosterCreationStrategy());
        interactor.execute(inputData);
    }
}
