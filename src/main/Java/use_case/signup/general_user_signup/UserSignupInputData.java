package use_case.signup.general_user_signup;

import java.util.List;

public class UserSignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final Integer age;
    private final List<String> interests;

    public UserSignupInputData(String username,
                               String password,
                               String repeatPassword,
                               String firstName,
                               String lastName,
                               Integer age,
                               String gender,
                               List<String> interests) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.repeatPassword = repeatPassword;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }
    String getRepeatPassword() { return repeatPassword; }
    String getGender() { return gender; }
    Integer getAge() { return age; }
    List<String> getInterests() { return interests; }
    String getFirstName() { return firstName; }
    String getLastName() { return lastName; }

}
