package views;

import data_access.InMemoryUserDataAccessObject;
import entity.EventPoster;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_approval.AdminApprovalController;
import interface_adapter.admin_approval.AdminApprovalPresenter;
import interface_adapter.admin_approval.AdminApprovalState;
import interface_adapter.admin_approval.AdminApprovalViewModel;
import use_case.admin_account_approval.AdminApprovalInteractor;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Approval Requests Screen for managing pending EventPoster approvals.
 */
public class ApprovalRequestsScreen extends JFrame implements PropertyChangeListener {
    private static final String VIEW_NAME = "Approval Requests";

    private final AdminApprovalViewModel adminApprovalViewModel;
    private AdminApprovalController adminApprovalController;

    private final JPanel requestsPanel;

    public ApprovalRequestsScreen(AdminApprovalViewModel adminApprovalViewModel) {
        this.adminApprovalViewModel = adminApprovalViewModel;
        this.adminApprovalViewModel.addPropertyChangeListener(this);

        setTitle(VIEW_NAME);
        setupFrame();

        // Title label
        JLabel titleLabel = new JLabel(VIEW_NAME, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Requests panel with scroll functionality
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(requestsPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        initializeRequests();
    }

    private void setupFrame() {
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeRequests() {
        loadRequests(adminApprovalViewModel.getState());
    }

    private void loadRequests(AdminApprovalState adminApprovalState) {
        List<EventPoster> unapprovedUsers = adminApprovalState.getUnapprovedUsers();
        requestsPanel.removeAll();

        for (EventPoster user : unapprovedUsers) {
            JPanel userPanel = createUserPanel(user);
            requestsPanel.add(userPanel);
        }

        SwingUtilities.invokeLater(() -> {
            requestsPanel.revalidate();
            requestsPanel.repaint();
        });
    }

    private JPanel createUserPanel(EventPoster user) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel userLabel = new JLabel(user.getUsername());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(e -> {
            adminApprovalController.approveUser(user.getUsername());
        });

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> {
            adminApprovalController.rejectUser(user.getUsername());
        });

        userPanel.add(userLabel);
        userPanel.add(Box.createHorizontalStrut(20)); // Space
        userPanel.add(approveButton);
        userPanel.add(Box.createHorizontalStrut(10)); // Space
        userPanel.add(rejectButton);

        return userPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            AdminApprovalState newState = (AdminApprovalState) evt.getNewValue();
            loadRequests(newState);
        }
    }

    public void setAdminApprovalController(AdminApprovalController adminApprovalController) {
        this.adminApprovalController = adminApprovalController;
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public static void main(String[] args) {
        // Setup dummy data
        AdminApprovalState adminApprovalState = new AdminApprovalState();
        adminApprovalState.setUnapprovedUsers(List.of(
                new EventPoster("johndoe", "password123", "TechOrg", "http://example.com", null),
                new EventPoster("janedoe", "password456", "ArtClub", "http://example.org", null)
        ));

        AdminApprovalViewModel adminApprovalViewModel = new AdminApprovalViewModel();
        adminApprovalViewModel.setState(adminApprovalState);

        AdminApprovalController adminApprovalController = new AdminApprovalController(new AdminApprovalInteractor(
                new AdminApprovalPresenter(adminApprovalViewModel, new ViewManagerModel()),
                new InMemoryUserDataAccessObject()
        ));

        ApprovalRequestsScreen approvalRequestsScreen = new ApprovalRequestsScreen(adminApprovalViewModel);
        approvalRequestsScreen.setAdminApprovalController(adminApprovalController);

        SwingUtilities.invokeLater(() -> approvalRequestsScreen.setVisible(true));
    }
}
