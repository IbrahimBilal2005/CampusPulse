package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Account;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.general_user_signup.UserSignupDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessInterface implements UserSignupDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface {

    private final Map<String, Account> users = new HashMap<>();

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(Account user) {
        users.put(user.getUsername(), user);}
}

