package use_case.admin_account_approval;

public class AdminApprovalInteractor {
    private final AdminApprovalOutputBoundary outputBoundary;
    private final AdminApprovalUserDataAccessInterface userDataAccess;

    public AdminApprovalInteractor(AdminApprovalOutputBoundary outputBoundary, AdminApprovalUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    public void approveUser(AdminApprovalInputData inputData) {
        try {
            // Validate if the admin has the correct role
            if (!userDataAccess.isAdmin(inputData.getAdminUid())) {
                outputBoundary.presentFailure("Permission denied: Only admins can approve users.");
                return;
            }

            // Approve the target user
            boolean success = userDataAccess.approveUserAsEventPoster(inputData.getTargetUid());
            if (success) {
                outputBoundary.presentSuccess(new AdminApprovalOutputData("User approved successfully."));
            } else {
                outputBoundary.presentFailure("Failed to approve user.");
            }
        } catch (Exception e) {
            outputBoundary.presentFailure("Error: " + e.getMessage());
        }
    }
}

