package use_case.signup.general_user_signup;

public class UserSignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String gender;
    private final Integer age;

    public UserSignupInputData(String username,
                               String password,
                               String repeatPassword,
                               String gender,
                               Integer age) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = age;
        this.gender = gender;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }
    String getRepeatPassword() { return repeatPassword; }
    String getGender() { return gender; }
    Integer getAge() { return age; }

}
