package entity;

import java.util.List;

/**
 * A general user in our program.
 */
public class User implements Account {

    private final String username;
    private  String password;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String gender;
    private final List<String> interests;

    public User(String username,
                String password,
                String firstName,
                String lastName,
                Integer age,
                String gender,
                List<String> interests) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
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

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUsername(String username) {

    }
}

