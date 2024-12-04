package use_case.admin_account_approval;

import entity.EventPoster;

import java.util.List;

public class AdminApprovalOutputData {
    private final String message;
    private final boolean approved;
    private List<EventPoster> unapprovedEventPosters;

    public AdminApprovalOutputData(List<EventPoster> unapprovedEventPosters, String message, boolean approved) {
        this.message = message;
        this.approved = approved;
        this.unapprovedEventPosters = unapprovedEventPosters;

    }

    public String getMessage() {
        return message;
    }

    public List<EventPoster> getUnapprovedEventPosters() { return unapprovedEventPosters; }

    public boolean getApproved() {
        return approved;
    }
}