package use_case.general_user_signup;

public class UserSignupOutputData {
    private final String username;

    private final boolean useCaseFailed;

    public UserSignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
