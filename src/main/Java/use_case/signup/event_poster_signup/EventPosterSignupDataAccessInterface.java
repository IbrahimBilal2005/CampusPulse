package use_case.signup.event_poster_signup;

import entity.Account;

public interface EventPosterSignupDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param eventPoster the event poster to save
     */
    void save(Account eventPoster);
}
