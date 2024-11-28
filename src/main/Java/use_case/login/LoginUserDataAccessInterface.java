package use_case.login;

import entity.Account;

public interface LoginUserDataAccessInterface {

    boolean nameExists(String username);

    void save(Account account);

    Account get(String username);

    String getCurrentUsername();

}
