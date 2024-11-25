package entity;

import java.util.List;

/**
 * A general user in our program.
 */
public class User implements Account {

    // TODO can we add an email authentification system?
    //  If this isn't an insane amount of extra work it would be a cool addition

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String gender;
    private final List<String> interests;
    private final String role;

    public User(String username,
                String password,
                String firstName,
                String lastName,
                Integer age,
                String gender,
                List<String> interests,
                String role) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public List<String> getInterests() {
        return this.interests;
    }

    public String getRole() { return this.role; }
}

