package interface_adapter.general_user_signup;

import use_case.general_user_signup.UserSignupInputBoundary;
import use_case.general_user_signup.UserSignupInputData;

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
                        Integer age) {

        final UserSignupInputData userSignupInputData = new UserSignupInputData(
                username, password, confirmPassword, gender, age);

        userSignupUseCaseInteractor.execute(userSignupInputData);

    }
}
