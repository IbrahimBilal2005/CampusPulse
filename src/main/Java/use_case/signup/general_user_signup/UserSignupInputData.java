package use_case.signup.general_user_signup;

import java.util.List;

public class UserSignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String gender;
    private final Integer age;
    private final List<String> interests;

    public UserSignupInputData(String username,
                               String password,
                               String repeatPassword,
                               String gender,
                               Integer age,
                               List<String> interests) {
        this.username = username;
        this.password = password;
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

}
