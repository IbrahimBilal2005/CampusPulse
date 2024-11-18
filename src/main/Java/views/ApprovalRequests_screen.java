package views;

import entity.User;
import entity.Role;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ApprovalRequests_screen extends JFrame {
    private List<User> pendingRequests;

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

            approveButton.addActionListener(e -> {
                user.setRole(Role.EVENT_HOSTER);
                JOptionPane.showMessageDialog(this, user.getUsername() + " is now an Event Hoster!");
                refreshUI();
            });

            rejectButton.addActionListener(e -> {
                pendingRequests.remove(user);
                JOptionPane.showMessageDialog(this, "Rejected " + user.getUsername() + "'s request.");
                refreshUI();
            });

            userPanel.add(userLabel);
            userPanel.add(approveButton);
            userPanel.add(rejectButton);
            requestsPanel.add(userPanel);
        }

        JScrollPane scrollPane = new JScrollPane(requestsPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void refreshUI() {
        dispose();
        new ApprovalRequests_screen(pendingRequests);
    }

    public static void main(String[] args) {
        // Dummy data
        List<User> pending = new ArrayList<>();
        pending.add(new User("Jerry", "jerry@utoronto.ca", "jerryu", Role.PENDING));
        new ApprovalRequests_screen(pending);
    }
}
