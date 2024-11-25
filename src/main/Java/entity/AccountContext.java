package entity;

public class AccountContext {
    private AccountCreationStrategy strategy;

    // Set the strategy dynamically
    public void setAccountCreationStrategy(AccountCreationStrategy strategy) {
        this.strategy = strategy;
    }

    // Create account using the current strategy
    public Account createAccount(String username, String password, Object... params) {
        if (strategy == null) {
            throw new IllegalStateException("Account creation strategy is not set.");
        }
        return strategy.createAccount(username, password, params);
    }
}
