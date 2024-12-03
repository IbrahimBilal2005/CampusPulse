package use_case.home;

import entity.User;

public class HomeScreenInputData {
    private final User user;

    public HomeScreenInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
