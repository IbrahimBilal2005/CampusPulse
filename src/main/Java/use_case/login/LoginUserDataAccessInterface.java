package use_case.login;

import entity.Account;

public interface LoginUserDataAccessInterface {

    boolean nameExists(String username);

    Account get(String username);

}
