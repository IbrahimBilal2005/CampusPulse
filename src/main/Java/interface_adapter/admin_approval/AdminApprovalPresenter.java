package interface_adapter.admin_approval;

import entity.Admin;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_event.MyEventsState;
import interface_adapter.delete_event.MyEventsViewModel;
import use_case.admin_account_approval.AdminApprovalOutputBoundary;
import use_case.admin_account_approval.AdminApprovalOutputData;

public class AdminApprovalPresenter implements AdminApprovalOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AdminApprovalViewModel adminApprovalViewModel;

    public AdminApprovalPresenter(AdminApprovalViewModel adminApprovalViewModel, ViewManagerModel viewManagerModel) {
        this.adminApprovalViewModel = adminApprovalViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentSuccess(AdminApprovalOutputData outputData) {
        final AdminApprovalState adminApprovalState = adminApprovalViewModel.getState();
        adminApprovalState.setUnapprovedUsers(outputData.getUnapprovedEventPosters());
        this.adminApprovalViewModel.setState(adminApprovalState);
        this.adminApprovalViewModel.firePropertyChanged();

        this.viewManagerModel.setState(adminApprovalViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentFailure(String errorMessage) {
        final AdminApprovalState adminApprovalState = adminApprovalViewModel.getState();
        adminApprovalState.setMessage(errorMessage);
        adminApprovalViewModel.firePropertyChanged();
    }
}