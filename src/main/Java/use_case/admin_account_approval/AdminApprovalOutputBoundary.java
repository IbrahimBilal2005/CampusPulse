package use_case.admin_account_approval;

public interface AdminApprovalOutputBoundary {
    void presentSuccess(AdminApprovalOutputData outputData);
    void presentFailure(String errorMessage);
}
