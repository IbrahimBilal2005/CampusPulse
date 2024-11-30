package use_case.new_event_post;

public class NewEventPostOutputData {
    private final boolean useCaseFailed; // Indicates if the use case failed

    public NewEventPostOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
