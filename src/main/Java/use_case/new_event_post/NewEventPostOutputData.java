package use_case.new_event_post;

public class NewEventPostOutputData {
    private final boolean useCaseFailed; // Indicates if the use case failed
    private final String message; // Error message (null if no error)

    public NewEventPostOutputData(boolean useCaseFailed, String message) {
        this.useCaseFailed = useCaseFailed;
        this.message = message;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String message() {
        return message;
    }
}
