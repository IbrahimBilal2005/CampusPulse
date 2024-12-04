package interface_adapter.admin_approval;

import entity.EventPoster;

import java.util.ArrayList;
import java.util.List;

public class AdminApprovalState {
    private String message;
    private List<EventPoster> unapprovedUsers = new ArrayList<>();

    public AdminApprovalState(AdminApprovalState copy) {
        this.message = copy.message;
        this.unapprovedUsers = copy.unapprovedUsers;
    }

    public AdminApprovalState() {}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<EventPoster> getUnapprovedUsers() { return unapprovedUsers; }
    public void setUnapprovedUsers(List<EventPoster> unapprovedUsers) { this.unapprovedUsers = unapprovedUsers; }
}