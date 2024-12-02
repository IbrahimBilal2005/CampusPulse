package use_case.admin_account_approval;

public interface AdminApprovalInputBoundary {
    void approveUser(AdminApprovalInputData inputData);
    void rejectUser(AdminApprovalInputData inputData); // Added reject method
}
