package use_case.admin_account_approval;

public class AdminApprovalOutputData {
    private final String message;

    public AdminApprovalOutputData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
