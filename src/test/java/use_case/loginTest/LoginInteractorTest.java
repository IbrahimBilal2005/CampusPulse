package use_case.loginTest;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeScreenViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LoginInteractorTest {
    private LoginPresenter presenter;
    private LoginInteractor interactor;
    private InMemoryUserDataAccessObject userDataAccess;


    @BeforeEach
    void setUp() {
        // Initialize the in-memory data access, view model, and presenter
        userDataAccess = new InMemoryUserDataAccessObject();
        userDataAccess.addAccount(new User("Tanay06", "Pulselife", "Tanay", "Langhe", 18, "Male", null)); // Add test account
        LoginViewModel viewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeScreenViewModel homeScreenViewModel = new HomeScreenViewModel();
        presenter = new LoginPresenter(viewModel, viewManagerModel, homeScreenViewModel);  // Real presenter
        interactor = new LoginInteractor(userDataAccess, presenter);

    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Tanay06", "Pulselife");
        interactor.execute(inputData);



    }

    @Test
    void failurePasswordMismatchTest() {

    }

    @Test
    void failureUserDoesNotExistTest() {

    }
}
