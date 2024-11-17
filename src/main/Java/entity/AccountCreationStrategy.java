package entity;

// Account creation strategy interface
public interface AccountCreationStrategy {
    Account createAccount(String username, String password, Object... params);
}