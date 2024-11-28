package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Account;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.UserSignupDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements UserSignupDataAccessInterface,
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

    @Override
    public Account getAccountByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(Account updatedAccount, String newPassword) {
        if (!users.containsKey(updatedAccount.getUsername())) {
            throw new IllegalArgumentException("Account not found."); // Handle non-existing accounts
        }

        Account oldAccount = users.get(updatedAccount.getUsername());
        oldAccount.setPassword(newPassword);
    }

    /**
     * Adds a test account to the in-memory store.
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        users.put(account.getUsername(), account);
    }
}

