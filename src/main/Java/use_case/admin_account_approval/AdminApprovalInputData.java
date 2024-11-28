package use_case.admin_account_approval;

public class AdminApprovalInputData {
    private final String adminUid;
    private final String targetUid;

    public AdminApprovalInputData(String adminUid, String targetUid) {
        this.adminUid = adminUid;
        this.targetUid = targetUid;
    }

    public String getAdminUid() {
        return adminUid;
    }

    public String getTargetUid() {
        return targetUid;
    }
}
