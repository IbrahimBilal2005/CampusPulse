package use_case.signup.event_poster_signup;

public class EventPosterSignupOutputData {
    private final String username;

    private final boolean useCaseFailed;

    public EventPosterSignupOutputData(String username, boolean useCaseFailed) {
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
