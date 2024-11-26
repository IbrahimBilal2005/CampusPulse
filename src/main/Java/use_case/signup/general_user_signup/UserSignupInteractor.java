package use_case.signup.general_user_signup;

import entity.Account;
import entity.AccountCreationStrategy;
import use_case.signup.UserSignupDataAccessInterface;

public class UserSignupInteractor implements UserSignupInputBoundary {

    private final UserSignupDataAccessInterface userDataAccessObject;
    private final UserSignupOutputBoundary userPresenter;
    private final AccountCreationStrategy accountCreator;

    public UserSignupInteractor(UserSignupDataAccessInterface userDataAccessObject,
                                UserSignupOutputBoundary userSignupOutputBoundary,
                                AccountCreationStrategy accountCreator) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userSignupOutputBoundary;
        this.accountCreator = accountCreator;
    }


    @Override
    public void execute(UserSignupInputData userSignupInputData) {
        if (userDataAccessObject.existsByName(userSignupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists");
        }
        else if (!userSignupInputData.getPassword().equals(userSignupInputData.getRepeatPassword())){
            userPresenter.prepareFailView("Passwords do not match");
        }
        else if (userSignupInputData.getAge() < 18) {
            userPresenter.prepareFailView("Age must be at least 18");
        }
        else if (userSignupInputData.getGender().isEmpty()) {
            userPresenter.prepareFailView("No gender selected");
        }
        else if (userSignupInputData.getInterests() == null) {
            userPresenter.prepareFailView("No interests selected");
        }
        else if (userSignupInputData.getFirstName().isEmpty() || !userSignupInputData.getFirstName().matches("[a-zA-Z]+")) {
            userPresenter.prepareFailView("Invalid first name");
        }
        else if (userSignupInputData.getLastName().isEmpty() || !userSignupInputData.getLastName().matches("[a-zA-Z]+")) {
            userPresenter.prepareFailView("Invalid last name");
        }
        else {
            final Account user = accountCreator.createAccount(
                    userSignupInputData.getUsername(),
                    userSignupInputData.getPassword(),
                    userSignupInputData.getFirstName(),
                    userSignupInputData.getLastName(),
                    userSignupInputData.getAge(),
                    userSignupInputData.getGender(),
                    userSignupInputData.getInterests());

            userDataAccessObject.save(user);
            final UserSignupOutputData signupOutputData= new UserSignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToBaseView() { userPresenter.switchToBaseView(); }
}