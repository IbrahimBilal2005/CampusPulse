package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.UserInterface;
import use_case.change_password.ChangePasswordCommonUserDataAccessInterface;
import use_case.login.LoginCommonUserDataAccessInterface;
import use_case.logout.LogoutCommonUserDataAccessInterface;
import use_case.signup.SignupCommonUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 * <<<TEMP STORAGE TO SEE IF PROGRAM IS RUNNING CORRECTLY.
 */
public class InMemoryUserDataAccessObject implements SignupCommonUserDataAccessInterface,
        LoginCommonUserDataAccessInterface,
        ChangePasswordCommonUserDataAccessInterface,
        LogoutCommonUserDataAccessInterface {

    private final Map<String, UserInterface> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(UserInterface user) {
        users.put(user.getName(), user);
    }

    @Override
    public UserInterface get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(UserInterface user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
