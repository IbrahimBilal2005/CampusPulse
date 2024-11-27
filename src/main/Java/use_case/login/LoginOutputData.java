package use_case.login;

public class LoginOutputData {
    private final boolean useCaseFailed;

    public LoginOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() { return useCaseFailed; }

}
