package use_case.admin_account_approval;

import entity.EventPoster;

import java.util.List;

public class AdminApprovalInteractor implements AdminApprovalInputBoundary{
    private final AdminApprovalOutputBoundary outputBoundary;
    private final AdminApprovalUserDataAccessInterface userDataAccess;

    public AdminApprovalInteractor(AdminApprovalOutputBoundary outputBoundary, AdminApprovalUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    public void approveUser(AdminApprovalInputData inputData) {
        // approve the user from the input data
        userDataAccess.setApproval(inputData.getTargetUid(), true);

        //get all the new unapproved users (should not contain the user from the input data)
        List<EventPoster> unapprovedUsers = userDataAccess.getUnapprovedUsers();

        //create the output data
        AdminApprovalOutputData outputData = new AdminApprovalOutputData(unapprovedUsers, "user approved successfully", true);

        // call the success presenter
        outputBoundary.presentSuccess(outputData);



    }

    public void rejectUser(AdminApprovalInputData inputData) {
        // reject the user from the input data
        userDataAccess.setApproval(inputData.getTargetUid(), false);

        //get all the new unapproved users (should contain the user from the input data)
        List<EventPoster> unapprovedUsers = userDataAccess.getUnapprovedUsers();

        //create the output data
        AdminApprovalOutputData outputData = new AdminApprovalOutputData(unapprovedUsers, "user rejected successfully", false);

        // call the success presenter
        outputBoundary.presentSuccess(outputData);

    }
}