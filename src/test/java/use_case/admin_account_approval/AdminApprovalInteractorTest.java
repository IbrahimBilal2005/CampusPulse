package use_case.admin_account_approval;

import entity.EventPoster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminApprovalInteractorTest {
    private TestOutputBoundary outputBoundary;
    private TestUserDataAccess userDataAccess;
    private AdminApprovalInteractor interactor;

    @BeforeEach
    void setUp() {
        outputBoundary = new TestOutputBoundary();
        userDataAccess = new TestUserDataAccess();
        userDataAccess.throwException = false;
        interactor = new AdminApprovalInteractor(outputBoundary, userDataAccess);
    }

    @Test
    void testApproveUserSuccess() {
        EventPoster user = new EventPoster("johndoe", "password123", "TechOrg", "http://example.com", null);
        userDataAccess.addUnapprovedUser(user);

        AdminApprovalInputData inputData = new AdminApprovalInputData("johndoe");
        interactor.approveUser(inputData);

        assertTrue(outputBoundary.success);
        assertEquals("user approved successfully", outputBoundary.outputData.getMessage());
        assertTrue(outputBoundary.outputData.getApproved());
        assertFalse(userDataAccess.unapprovedUsers.contains(user));
    }

    @Test
    void testRejectUserSuccess() {
        EventPoster user = new EventPoster("janedoe", "password456", "ArtClub", "http://example.org", null);
        userDataAccess.addUnapprovedUser(user);

        AdminApprovalInputData inputData = new AdminApprovalInputData("janedoe");
        interactor.rejectUser(inputData);

        assertTrue(outputBoundary.success);
        assertEquals("user rejected successfully", outputBoundary.outputData.getMessage());
        assertFalse(outputBoundary.outputData.getApproved());
        assertTrue(userDataAccess.unapprovedUsers.contains(user));
    }

    private static class TestOutputBoundary implements AdminApprovalOutputBoundary {
        boolean success;
        AdminApprovalOutputData outputData;
        String errorMessage;

        @Override
        public void presentSuccess(AdminApprovalOutputData outputData) {
            this.success = true;
            this.outputData = outputData;
        }

        @Override
        public void presentFailure(String errorMessage) {
            this.success = false;
            this.errorMessage = errorMessage;
        }
    }

    private static class TestUserDataAccess implements AdminApprovalUserDataAccessInterface {
        List<EventPoster> unapprovedUsers = new ArrayList<>();
        boolean throwException = false;

        @Override
        public boolean setApproval(String uid, boolean approvalState) {
            if (throwException) {
                throw new RuntimeException("Simulated exception during approval/rejection");
            }

            for (EventPoster user : unapprovedUsers) {
                if (user.getUsername().equals(uid)) {
                    if (approvalState) {
                        unapprovedUsers.remove(user);
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean rejectUserAsEventPoster(String uid) {
            return setApproval(uid, false);
        }

        @Override
        public List<EventPoster> getUnapprovedUsers() {
            return new ArrayList<>(unapprovedUsers);
        }

        void addUnapprovedUser(EventPoster user) {
            unapprovedUsers.add(user);
        }
    }
}
