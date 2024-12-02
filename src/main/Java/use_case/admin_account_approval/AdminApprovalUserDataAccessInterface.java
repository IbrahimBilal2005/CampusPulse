package use_case.admin_account_approval;

public interface AdminApprovalUserDataAccessInterface {
    boolean approveUserAsEventPoster(String uid);
    boolean rejectUserAsEventPoster(String uid); // Renamed for consistency
}
