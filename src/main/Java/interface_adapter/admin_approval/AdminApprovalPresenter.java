package interface_adapter.admin_approval;

import use_case.admin_account_approval.AdminApprovalOutputBoundary;
import use_case.admin_account_approval.AdminApprovalOutputData;

public class AdminApprovalPresenter implements AdminApprovalOutputBoundary {
    private final AdminApprovalState state;

    public AdminApprovalPresenter(AdminApprovalState state) {
        this.state = state;
    }

    @Override
    public void presentSuccess(AdminApprovalOutputData outputData) {
        state.setMessage(outputData.getMessage());
    }

    @Override
    public void presentFailure(String errorMessage) {
        state.setMessage(errorMessage);
    }
}
