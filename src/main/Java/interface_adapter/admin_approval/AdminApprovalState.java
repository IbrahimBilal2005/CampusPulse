package interface_adapter.admin_approval;

import entity.Event;
import entity.EventPoster;

import java.util.List;

public class AdminApprovalState {
    private List<EventPoster> eventPosters;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
