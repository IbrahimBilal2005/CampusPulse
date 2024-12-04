package interface_adapter.admin_approval;

import interface_adapter.ViewModel;

/**
 * View Model for the Admin Approval view.
 */
public class AdminApprovalViewModel extends ViewModel<AdminApprovalState> {

    public AdminApprovalViewModel() {
        super("Admin Approval");
        setState(new AdminApprovalState());
    }
}