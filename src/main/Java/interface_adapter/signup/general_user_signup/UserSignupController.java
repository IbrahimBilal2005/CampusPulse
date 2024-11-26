package interface_adapter.signup.general_user_signup;

import use_case.signup.general_user_signup.UserSignupInputBoundary;
import use_case.signup.general_user_signup.UserSignupInputData;

import java.util.List;

public class UserSignupController {

    private final UserSignupInputBoundary userSignupUseCaseInteractor;

    public UserSignupController(UserSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the EventPoster Signup Use Case.
     * @param username username to sign up
     * @param password the password
     * @param confirmPassword the confirmed password
     * @param gender the gender of the user
     * @param age the age of the user.
     */
    public void execute(String username,
                        String password,
                        String confirmPassword,
                        String gender,
                        Integer age,
                        List<String> interests) {

        final UserSignupInputData userSignupInputData = new UserSignupInputData(
                username, password, confirmPassword, gender, age, interests);

        userSignupUseCaseInteractor.execute(userSignupInputData);

    }

    public void switchToBaseView(){ userSignupUseCaseInteractor.switchToBaseView(); }

    public void switchToLoginView(){ userSignupUseCaseInteractor.switchToLoginView(); }
}
