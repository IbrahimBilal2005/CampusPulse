package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public UserInterface create(String name, String password) {
        return new CommonUser(name, password);
    }
}
