package use_case.admin_account_approval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
        AdminApprovalInputData inputData = new AdminApprovalInputData("validUid");
        interactor.approveUser(inputData);

        assertTrue(outputBoundary.success);
        assertEquals("User approved successfully", outputBoundary.outputData.getMessage());
        assertTrue(outputBoundary.outputData.getApproved());
        assertTrue(userDataAccess.approvedUsers.contains("validUid"));
    }

    @Test
    void testApproveUserFailure() {
        AdminApprovalInputData inputData = new AdminApprovalInputData("invalidUid");
        interactor.approveUser(inputData);

        assertFalse(outputBoundary.success);
        assertEquals("Failed to approve user.", outputBoundary.errorMessage);
        assertFalse(userDataAccess.approvedUsers.contains("invalidUid"));
    }

    @Test
    void testRejectUserSuccess() {
        AdminApprovalInputData inputData = new AdminApprovalInputData("validUid");
        interactor.rejectUser(inputData);

        assertTrue(outputBoundary.success);
        assertEquals("User rejected successfully", outputBoundary.outputData.getMessage());
        assertTrue(outputBoundary.outputData.getApproved());
        assertTrue(userDataAccess.rejectedUsers.contains("validUid"));
    }

    @Test
    void testRejectUserFailure() {
        AdminApprovalInputData inputData = new AdminApprovalInputData("invalidUid");
        interactor.rejectUser(inputData);

        assertFalse(outputBoundary.success);
        assertEquals("Failed to reject user.", outputBoundary.errorMessage);
        assertFalse(userDataAccess.rejectedUsers.contains("invalidUid"));
    }

    @Test
    void testApproveUserException() {
        // Arrange: Trigger exception in the data access layer
        userDataAccess.throwException = true;

        // Act: Try approving a user
        AdminApprovalInputData inputData = new AdminApprovalInputData("validUid");
        interactor.approveUser(inputData);

        // Assert: Ensure the exception is handled gracefully
        assertFalse(outputBoundary.success);
        assertTrue(outputBoundary.errorMessage.startsWith("Error:"));
    }

    @Test
    void testRejectUserException() {
        // Arrange: Trigger exception in the data access layer
        userDataAccess.throwException = true;

        // Act: Try rejecting a user
        AdminApprovalInputData inputData = new AdminApprovalInputData("validUid");
        interactor.rejectUser(inputData);

        // Assert: Ensure the exception is handled gracefully
        assertFalse(outputBoundary.success);
        assertTrue(outputBoundary.errorMessage.startsWith("Error:"));
    }

    // Concrete implementation of AdminApprovalOutputBoundary
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

    // Concrete implementation of AdminApprovalUserDataAccessInterface
    private static class TestUserDataAccess implements AdminApprovalUserDataAccessInterface {
        Set<String> approvedUsers = new HashSet<>();
        Set<String> rejectedUsers = new HashSet<>();
        boolean throwException = false;

        @Override
        public boolean approveUserAsEventPoster(String uid) {
            if (throwException) {
                throw new RuntimeException("Simulated exception during approval");
            }
            if ("validUid".equals(uid)) {
                approvedUsers.add(uid);
                return true;
            }
            return false;
        }

        @Override
        public boolean rejectUserAsEventPoster(String uid) {
            if (throwException) {
                throw new RuntimeException("Simulated exception during rejection");
            }
            if ("validUid".equals(uid)) {
                rejectedUsers.add(uid);
                return true;
            }
            return false;
        }
    }
}
