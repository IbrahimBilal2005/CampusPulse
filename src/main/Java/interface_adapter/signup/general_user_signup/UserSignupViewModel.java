package interface_adapter.signup.general_user_signup;

import interface_adapter.ViewModel;

public class UserSignupViewModel extends ViewModel<UserSignupState> {

    public static final String GENDER_LABEL = "Gender";
    public static final String AGE_LABEL = "Age";

    public UserSignupViewModel() {
        super("User Signup Screen");
        setState(new UserSignupState());
    }
}
