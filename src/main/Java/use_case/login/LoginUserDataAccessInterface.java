package use_case.login;

import entity.Account;

/**
 * Is the data access interface used to make sure the account actually exists
 */
public interface LoginUserDataAccessInterface {

    boolean nameExists(String username);

    Account get(String username);

}
