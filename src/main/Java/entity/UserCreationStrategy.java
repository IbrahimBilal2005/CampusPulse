package entity;

import java.util.List;

// Strategy for creating a User
public class UserCreationStrategy implements AccountCreationStrategy {

    @Override
    public Account createAccount(String username, String password, Object... params) {
        // Assuming params are [firstName, lastName, age, gender, interests]
        String firstName = (String) params[0];
        String lastName = (String) params[1];
        Integer age = (Integer) params[2];
        String gender = (String) params[3];
        List<String> interests = (List<String>) params[4];

        return new User(username, password, firstName, lastName, age, gender, interests);
    }
}