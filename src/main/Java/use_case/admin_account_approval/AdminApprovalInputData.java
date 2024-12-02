package use_case.admin_account_approval;

public class AdminApprovalInputData {
    private final String targetUid;

    public AdminApprovalInputData(String targetUid) {
        this.targetUid = targetUid;
    }

    public String getTargetUid() {
        return targetUid;
    }
}
