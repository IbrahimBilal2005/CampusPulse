package interface_adapter.admin_approval;

public class AdminApprovalViewModel {
    private final AdminApprovalState state;

    public AdminApprovalViewModel(AdminApprovalState state) {
        this.state = state;
    }

    public String getMessage() {
        return state.getMessage();
    }
}
