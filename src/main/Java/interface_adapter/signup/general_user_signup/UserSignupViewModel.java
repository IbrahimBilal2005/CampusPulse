package interface_adapter.signup.general_user_signup;

import interface_adapter.ViewModel;

import java.util.List;

public class UserSignupViewModel extends ViewModel<UserSignupState> {

    public static final String AGE_LABEL = "Age";
    public static final String TITLE_LABEL = "General User Signup";
    public static final String INTEREST_SELECTION_LABEL = "Select your Interests";
    public static final List<String> INTEREST_SELECTION_OPTIONS = List.of("sports", "music", "visual art");
    public static final String FIRST_NAME_LABEL = "First Name";
    public static final String LAST_NAME_LABEL = "Last Name";
    public static final String GENDER_LABEL = "Gender";
    public static final List<String> GENDER_SELECTION_OPTIONS = List.of("male", "female", "other");

    public UserSignupViewModel() {
        super("User Signup Screen");
        setState(new UserSignupState());
    }
}
