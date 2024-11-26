package interface_adapter.signup.general_user_signup;

import java.util.List;

/**
 * The state for the Signup View Model.
 */
public class UserSignupState {
    private String username = "";
    private String usernameError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;
    private String gender = "";
    private Integer age;
    private String ageError;
    private List<String> interest;

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public String getGender() {return gender;}

    public Integer getAge() { return age; }

    public String getAgeError() { return ageError; }

    public List<String>  getInterests() { return interest; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public void setGender(String gender) { this.gender = gender; }

    public void setAge(Integer age) { this.age = age; }

    public void setAgeError(String ageError) { this.ageError = ageError; }

    public void setInterests(List<String> interest) { this.interest = interest; }

    @Override
    public String toString() {
        return "UserSignupState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + ", gender='" + gender + '\''
                + ", age='" + age + '\''
                + ", interest='" + interest + '\''
                + '}';
    }
}
