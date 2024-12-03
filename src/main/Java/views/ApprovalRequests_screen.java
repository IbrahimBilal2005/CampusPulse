package views;

import entity.User;
import interface_adapter.admin_approval.AdminApprovalController;
import interface_adapter.admin_approval.AdminApprovalPresenter;
import interface_adapter.admin_approval.AdminApprovalState;
import use_case.admin_account_approval.AdminApprovalInteractor;
import use_case.admin_account_approval.AdminApprovalInputData;
import use_case.admin_account_approval.AdminApprovalUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApprovalRequests_screen extends JFrame {
    private List<User> pendingRequests;
    private AdminApprovalController approvalController;

    public ApprovalRequests_screen(List<User> pendingRequests) {
        this.pendingRequests = pendingRequests;

        // Frame setup
        setTitle("Approval Requests");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Pending Event Hoster Requests", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Pending requests panel
        JPanel requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));

        for (User user : pendingRequests) {
            JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel userLabel = new JLabel(user.toString());
            JButton approveButton = new JButton("Approve");
            JButton rejectButton = new JButton("Reject");

            userPanel.add(userLabel);
            userPanel.add(approveButton);
            userPanel.add(rejectButton);
            requestsPanel.add(userPanel);

            // Approve Button Logic
            approveButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(approveButton)) {
                                approvalController.approveUser(user.getUsername());
                                refreshUI();
                            }
                        }
                    }
            );

            // Reject Button Logic
            rejectButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(rejectButton)) {
                                approvalController.rejectUser(user.getUsername());
                                refreshUI();
                            }
                        }
                    }
            );
        }

        JScrollPane scrollPane = new JScrollPane(requestsPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setApprovalController(AdminApprovalController approvalController) {
        this.approvalController = approvalController;
    }

    private void refreshUI() {
        dispose();
        new ApprovalRequests_screen(pendingRequests).setApprovalController(approvalController);
    }

    public static void main(String[] args) {
        // Step 1: Create Dummy Data for Pending Requests
        List<User> pendingRequests = new ArrayList<>();
        pendingRequests.add(new User(
                "johndoe",             // username
                "password123",         // password
                "John",                // firstName
                "Doe",                 // lastName
                20,                    // age
                "Male",                // gender
                List.of("tech", "art") // interests
        ));
        pendingRequests.add(new User(
                "janedoe",             // username
                "password456",         // password
                "Jane",                // firstName
                "Doe",                 // lastName
                22,                    // age
                "Female",              // gender
                List.of("sports", "music") // interests
        ));

        // Step 2: Create the Required Components for AdminApprovalController
        AdminApprovalState adminApprovalState = new AdminApprovalState();
        AdminApprovalPresenter presenter = new AdminApprovalPresenter(adminApprovalState);

        AdminApprovalUserDataAccessInterface dataAccess = new AdminApprovalUserDataAccessInterface() {
            private final Set<String> approvedUsers = new HashSet<>();
            private final Set<String> rejectedUsers = new HashSet<>();

            @Override
            public boolean approveUserAsEventPoster(String uid) {
                if ("johndoe".equals(uid) || "janedoe".equals(uid)) {
                    approvedUsers.add(uid);
                    return true;
                }
                return false;
            }

            @Override
            public boolean rejectUserAsEventPoster(String uid) {
                if ("johndoe".equals(uid) || "janedoe".equals(uid)) {
                    rejectedUsers.add(uid);
                    return true;
                }
                return false;
            }
        };

        AdminApprovalInteractor interactor = new AdminApprovalInteractor(presenter, dataAccess);
        AdminApprovalController controller = new AdminApprovalController(interactor);

        // Step 3: Create and Show the ApprovalRequests_screen with Controller
        SwingUtilities.invokeLater(() -> {
            ApprovalRequests_screen screen = new ApprovalRequests_screen(pendingRequests);
            screen.setApprovalController(controller);
            screen.setVisible(true);
        });
    }
}
