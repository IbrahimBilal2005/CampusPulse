package use_case.admin_account_approval;

public interface AdminApprovalUserDataAccessInterface {
    boolean isAdmin(String uid);
    boolean approveUserAsEventPoster(String uid);
}
