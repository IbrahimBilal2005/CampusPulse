package entity;

/**
 * A strategy for creating an {@link Admin} account.
 * <p>
 * This class implements the {@link AccountCreationStrategy} interface and provides
 * the logic to create an {@link Admin} object with the provided username and password.
 * </p>
 */
public class AdminCreationStrategy implements AccountCreationStrategy {

    /**
     * Creates an {@link Admin} account with the specified username and password.
     *
     * @param username the admin's username.
     * @param password the admin's password.
     *
     * @return a new {@link Admin} instance created with the provided username and password.
     */
    @Override
    public Account createAccount(String username, String password, Object... params) {
        // No additional parameters are needed for Admin creation at this point
        return new Admin(username, password);
    }
}
