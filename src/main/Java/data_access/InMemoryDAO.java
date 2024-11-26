package data_access;

import entity.Account;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;


public class InMemoryDAO implements ChangePasswordUserDataAccessInterface {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account getAccountByUsername(String username) {
        return accounts.get(username);
    }

    @Override
    public void changePassword(Account updatedAccount) {
        if (!accounts.containsKey(updatedAccount.getUsername())) {
            throw new IllegalArgumentException("Account not found."); // Handle non-existing accounts
        }
        accounts.put(updatedAccount.getUsername(), updatedAccount);
    }

    /**
     * Adds a test account to the in-memory store.
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
    }
}
