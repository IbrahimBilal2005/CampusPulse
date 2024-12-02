package use_case.admin_account_approval;

public class AdminApprovalOutputData {
    private final String message;
    private final boolean approved;

    public AdminApprovalOutputData(String message, boolean approved) {
        this.message = message;
        this.approved = approved;
    }

    public String getMessage() {
        return message;
    }

    public boolean getApproved() {
        return approved;
    }
}
