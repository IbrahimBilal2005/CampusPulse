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
        interactor = new AdminApprovalInteractor(outputBoundary, userDataAccess);
    }

    @Test
    void testApproveUserSuccess() {
        // Arrange: Add a user to the unapproved users
        userDataAccess.addUnapprovedUser(new EventPoster("johndoe", "password123", "TechOrg", "http://example.com", null));

        // Act: Approve the user
        AdminApprovalInputData inputData = new AdminApprovalInputData("johndoe");
        interactor.approveUser(inputData);

        // Assert: User is approved and no longer in the unapproved list
        assertTrue(outputBoundary.success);
        assertEquals("user approved successfully", outputBoundary.outputData.getMessage());
        assertTrue(outputBoundary.outputData.getApproved());
        assertFalse(userDataAccess.unapprovedUsers.stream().anyMatch(user -> user.getUsername().equals("johndoe")));
    }

    @Test
    void testApproveUserFailure() {
        // Act: Attempt to approve a user not in the unapproved list
        AdminApprovalInputData inputData = new AdminApprovalInputData("invalidUser");
        interactor.approveUser(inputData);

        // Assert: Failure message is sent
        assertFalse(outputBoundary.success);
        assertEquals("Failed to approve user.", outputBoundary.errorMessage);
    }

    @Test
    void testRejectUserSuccess() {
        // Arrange: Add a user to the unapproved users
        userDataAccess.addUnapprovedUser(new EventPoster("janedoe", "password456", "ArtClub", "http://example.org", null));

        // Act: Reject the user
        AdminApprovalInputData inputData = new AdminApprovalInputData("janedoe");
        interactor.rejectUser(inputData);

        // Assert: User remains in the unapproved list after rejection
        assertTrue(outputBoundary.success);
        assertEquals("user rejected successfully", outputBoundary.outputData.getMessage());
        assertTrue(outputBoundary.outputData.getApproved());
        assertTrue(userDataAccess.unapprovedUsers.stream().anyMatch(user -> user.getUsername().equals("janedoe")));
    }

    @Test
    void testRejectUserFailure() {
        // Act: Attempt to reject a user not in the unapproved list
        AdminApprovalInputData inputData = new AdminApprovalInputData("invalidUser");
        interactor.rejectUser(inputData);

        // Assert: Failure message is sent
        assertFalse(outputBoundary.success);
        assertEquals("Failed to reject user.", outputBoundary.errorMessage);
    }

    @Test
    void testApproveUserException() {
        // Arrange: Set the data access to throw an exception
        userDataAccess.throwException = true;

        // Act: Attempt to approve a user
        AdminApprovalInputData inputData = new AdminApprovalInputData("johndoe");
        interactor.approveUser(inputData);

        // Assert: Exception is handled gracefully
        assertFalse(outputBoundary.success);
        assertTrue(outputBoundary.errorMessage.startsWith("Error:"));
    }

    @Test
    void testRejectUserException() {
        // Arrange: Set the data access to throw an exception
        userDataAccess.throwException = true;

        // Act: Attempt to reject a user
        AdminApprovalInputData inputData = new AdminApprovalInputData("janedoe");
        interactor.rejectUser(inputData);

        // Assert: Exception is handled gracefully
        assertFalse(outputBoundary.success);
        assertTrue(outputBoundary.errorMessage.startsWith("Error:"));
    }

    // Test implementation of AdminApprovalOutputBoundary
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

    // Test implementation of AdminApprovalUserDataAccessInterface
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
