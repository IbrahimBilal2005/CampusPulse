import data_access.InMemoryDAOCP;
import entity.Account;
import entity.User;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.change_password.ChangePasswordInputData;
import use_case.change_password.ChangePasswordInteractor;

import static org.junit.jupiter.api.Assertions.*;


class ChangePasswordInteractorTest {
    private ChangePasswordInteractor interactor;
    private ChangePasswordPresenter presenter;
    private InMemoryDAOCP userDataAccess;

    @BeforeEach
    void setUp() {
        // Initialize the in-memory data access, view model, and presenter
        userDataAccess = new InMemoryDAOCP();
        ChangePasswordViewModel viewModel = new ChangePasswordViewModel();
        presenter = new ChangePasswordPresenter(viewModel);  // Real presenter
        interactor = new ChangePasswordInteractor(userDataAccess, presenter);

        // Add test accounts
        userDataAccess.addAccount(new User("user1", "oldPassword", "John", "Doe", 25, "Male", null));
        userDataAccess.addAccount(new User("user2", "password123", "Jane", "Smith", 30, "Female", null));

    }

    @Test
    void testSuccessfulPasswordChange() {
        // Arrange: Test input data for valid password change
        ChangePasswordInputData inputData = new ChangePasswordInputData("user1", "oldPassword", "newPassword", "newPassword");

        // Act: Execute the interactor
        interactor.execute(inputData);


        // Verify that the view model's error messages were cleared
        ChangePasswordState state = presenter.getViewModel().getState();
        assertNull(state.getCurrentPasswordError());
        assertNull(state.getNewPasswordError());
        assertNull(state.getConfirmPasswordError());

        // Verify the password was updated in the data store
        Account updatedUser = userDataAccess.getAccountByUsername("user1");
        assertEquals("newPassword", updatedUser.getPassword());
    }

    @Test
    void testInvalidCurrentPassword() {
        // Arrange: Test input data for incorrect current password
        ChangePasswordInputData inputData = new ChangePasswordInputData("user1", "wrongPassword", "newPassword", "newPassword");

        // Act: Execute the interactor
        interactor.execute(inputData);


        // Verify that the failure message was set in the view model
        ChangePasswordState state = presenter.getViewModel().getState();
        assertEquals("Incorrect current password.", state.getConfirmPasswordError());

        // Verify that the password was not updated
        Account updatedUser = userDataAccess.getAccountByUsername("user1");
        assertEquals("oldPassword", updatedUser.getPassword());
    }

    @Test
    void testNonMatchingNewAndConfirmPasswords() {
        // Arrange: Test input data for mismatched new and confirm passwords
        ChangePasswordInputData inputData = new ChangePasswordInputData("user1", "oldPassword", "newPassword", "differentPassword");

        // Act: Execute the interactor
        interactor.execute(inputData);


        // Verify that the failure message was set in the view model
        ChangePasswordState state = presenter.getViewModel().getState();
        assertEquals("New password and confirmation do not match.", state.getConfirmPasswordError());

        // Verify that the password was not updated
        Account updatedUser = userDataAccess.getAccountByUsername("user1");
        assertEquals("oldPassword", updatedUser.getPassword());
    }

    @Test
    void testUserNotFound() {
        // Arrange: Test input data for a username that doesn't exist
        ChangePasswordInputData inputData = new ChangePasswordInputData("nonexistentUser", "somePassword", "newPassword", "newPassword");

        // Act: Execute the interactor
        interactor.execute(inputData);


        // Verify that the failure message was set in the view model
        ChangePasswordState state = presenter.getViewModel().getState();
        assertEquals("Account not found.", state.getConfirmPasswordError());
    }

}
