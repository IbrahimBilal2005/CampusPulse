package use_case.admin_account_approval;

import entity.EventPoster;

import java.util.List;

public interface AdminApprovalUserDataAccessInterface {
    boolean setApproval(String uid, boolean approvalState);
    boolean rejectUserAsEventPoster(String uid);
    List<EventPoster> getUnapprovedUsers();
}