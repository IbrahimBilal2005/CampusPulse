package use_case.admin_account_approval;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminApprovalInteractorTest {

    // In-memory implementation of the data access interface
    static class InMemoryAdminApprovalDataAccess implements AdminApprovalUserDataAccessInterface {
        private final List<String> approvedUsers = new ArrayList<>();
        private final List<String> pendingUsers;

        public InMemoryAdminApprovalDataAccess(List<String> pendingUsers) {
            this.pendingUsers = pendingUsers;
        }

        @Override
        public boolean approveUserAsEventPoster(String uid) {
            if (pendingUsers.contains(uid)) {
                approvedUsers.add(uid);
                pendingUsers.remove(uid);
                return true;
            }
            return false;
        }

        @Override
        public boolean rejectUserAsEventPoster(String uid) {
            return pendingUsers.remove(uid);
        }

        public List<String> getApprovedUsers() {
            return approvedUsers;
        }

        public List<String> getPendingUsers() {
            return pendingUsers;
        }
    }

    @Test
    void approveUser_Success() {
        List<String> pendingUsers = new ArrayList<>(List.of("user1", "user2", "user3"));
        InMemoryAdminApprovalDataAccess dataAccess = new InMemoryAdminApprovalDataAccess(pendingUsers);

        AdminApprovalOutputBoundary successPresenter = new AdminApprovalOutputBoundary() {
            @Override
            public void presentSuccess(AdminApprovalOutputData outputData) {
                assertEquals("User approved successfully", outputData.getMessage());
                assertTrue(outputData.getApproved());
            }

            @Override
            public void presentFailure(String errorMessage) {
                fail("Approval should have succeeded.");
            }
        };

        AdminApprovalInteractor interactor = new AdminApprovalInteractor(successPresenter, dataAccess);

        AdminApprovalInputData inputData = new AdminApprovalInputData("user1");
        interactor.approveUser(inputData);

        assertEquals(2, dataAccess.getPendingUsers().size());
        assertTrue(dataAccess.getApprovedUsers().contains("user1"));
    }

    @Test
    void approveUser_Failure() {
        List<String> pendingUsers = new ArrayList<>(List.of("user2", "user3"));
        InMemoryAdminApprovalDataAccess dataAccess = new InMemoryAdminApprovalDataAccess(pendingUsers);

        AdminApprovalOutputBoundary failurePresenter = new AdminApprovalOutputBoundary() {
            @Override
            public void presentSuccess(AdminApprovalOutputData outputData) {
                fail("Approval should have failed.");
            }

            @Override
            public void presentFailure(String errorMessage) {
                assertEquals("Failed to approve user.", errorMessage);
            }
        };

        AdminApprovalInteractor interactor = new AdminApprovalInteractor(failurePresenter, dataAccess);

        AdminApprovalInputData inputData = new AdminApprovalInputData("nonexistentUser");
        interactor.approveUser(inputData);

        assertEquals(2, dataAccess.getPendingUsers().size());
        assertFalse(dataAccess.getApprovedUsers().contains("nonexistentUser"));
    }

    @Test
    void rejectUser_Success() {
        List<String> pendingUsers = new ArrayList<>(List.of("user1", "user2", "user3"));
        InMemoryAdminApprovalDataAccess dataAccess = new InMemoryAdminApprovalDataAccess(pendingUsers);

        AdminApprovalOutputBoundary successPresenter = new AdminApprovalOutputBoundary() {
            @Override
            public void presentSuccess(AdminApprovalOutputData outputData) {
                assertEquals("User rejected successfully", outputData.getMessage());
                assertTrue(outputData.getApproved()); // Indicates rejection success
            }

            @Override
            public void presentFailure(String errorMessage) {
                fail("Rejection should have succeeded.");
            }
        };

        AdminApprovalInteractor interactor = new AdminApprovalInteractor(successPresenter, dataAccess);

        AdminApprovalInputData inputData = new AdminApprovalInputData("user3");
        interactor.rejectUser(inputData);

        assertEquals(2, dataAccess.getPendingUsers().size());
        assertFalse(dataAccess.getPendingUsers().contains("user3"));
    }

    @Test
    void rejectUser_Failure() {
        List<String> pendingUsers = new ArrayList<>(List.of("user1", "user2"));
        InMemoryAdminApprovalDataAccess dataAccess = new InMemoryAdminApprovalDataAccess(pendingUsers);

        AdminApprovalOutputBoundary failurePresenter = new AdminApprovalOutputBoundary() {
            @Override
            public void presentSuccess(AdminApprovalOutputData outputData) {
                fail("Rejection should have failed.");
            }

            @Override
            public void presentFailure(String errorMessage) {
                assertEquals("Failed to reject user.", errorMessage);
            }
        };

        AdminApprovalInteractor interactor = new AdminApprovalInteractor(failurePresenter, dataAccess);

        AdminApprovalInputData inputData = new AdminApprovalInputData("nonexistentUser");
        interactor.rejectUser(inputData);

        assertEquals(2, dataAccess.getPendingUsers().size());
    }
}
