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
            // Approve the target user
            boolean success = userDataAccess.approveUserAsEventPoster(inputData.getTargetUid());
            if (success) {
                AdminApprovalOutputData outputData = new AdminApprovalOutputData("User approved successfully", true);
                outputBoundary.presentSuccess(outputData);
            } else {
                outputBoundary.presentFailure("Failed to approve user.");
            }
        } catch (Exception e) {
            outputBoundary.presentFailure("Error: " + e.getMessage());
        }
    }
}

