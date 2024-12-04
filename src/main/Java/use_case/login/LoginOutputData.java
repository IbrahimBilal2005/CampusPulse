package use_case.login;

/**
 * Output data for the login use case
 */
public class LoginOutputData {
    private final boolean useCaseFailed;

    public LoginOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    // returns the value of useCaseFailed
    public boolean isUseCaseFailed() { return useCaseFailed; }

}
