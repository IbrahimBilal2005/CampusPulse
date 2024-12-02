package interface_adapter.admin_approval;

import use_case.admin_account_approval.AdminApprovalInputBoundary;
import use_case.admin_account_approval.AdminApprovalInputData;

public class AdminApprovalController {
    private final AdminApprovalInputBoundary inputBoundary;

    public AdminApprovalController(AdminApprovalInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void approveUser(String targetUid) {
        AdminApprovalInputData inputData = new AdminApprovalInputData(targetUid);
        inputBoundary.approveUser(inputData);
    }

    public void rejectUser(String targetUid) {
        AdminApprovalInputData inputData = new AdminApprovalInputData(targetUid);
        inputBoundary.rejectUser(inputData); // Calls the reject logic
    }
}
