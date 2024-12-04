package views;

import data_access.InMemoryUserDataAccessObject;
import entity.EventPoster;
import interface_adapter.ViewManagerModel;
import interface_adapter.admin_approval.AdminApprovalController;
import interface_adapter.admin_approval.AdminApprovalPresenter;
import interface_adapter.admin_approval.AdminApprovalViewModel;
import interface_adapter.admin_approval.AdminApprovalState;
import use_case.admin_account_approval.AdminApprovalInteractor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ApprovalRequests_screen extends JFrame {
    private static final String VIEW_NAME = "Approval Requests";

    private final JPanel requestsPanel;
    private final JButton backButton;
    private AdminApprovalController approvalController;
    private final AdminApprovalViewModel adminApprovalViewModel;

    public ApprovalRequests_screen(AdminApprovalViewModel adminApprovalViewModel) {
        this.adminApprovalViewModel = adminApprovalViewModel;

        setTitle(VIEW_NAME);
        setupFrame();

        // Title label
        JLabel titleLabel = new JLabel(VIEW_NAME, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button
        backButton = new JButton("Back");

        // Panel for holding back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(backButton);

        // Requests panel with BoxLayout for vertical stacking
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));

        // Add a scroll pane to handle a large number of requests
        JScrollPane scrollPane = new JScrollPane(requestsPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the layout for the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH); // Title at the top
        add(scrollPane, BorderLayout.CENTER); // Requests list
        add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom

        // Load the initial list of requests
        initializeRequests();
    }

    private void setupFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height - 100); // Avoid taskbar overlap
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
    }

    private void initializeRequests() {
        List<EventPoster> pendingRequests = adminApprovalViewModel.getState().getUnapprovedUsers();
        requestsPanel.removeAll();

        for (EventPoster eventPoster : pendingRequests) {
            requestsPanel.add(createRequestPanel(eventPoster));
            requestsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between requests
        }

        SwingUtilities.invokeLater(() -> {
            requestsPanel.revalidate();
            requestsPanel.repaint();
        });
    }

    private JPanel createRequestPanel(EventPoster eventPoster) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // EventPoster details
        JLabel userLabel = new JLabel("Username: " + eventPoster.getUsername() + " | Organization: " + eventPoster.getOrganizationName());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Approve button
        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(evt -> {
            approvalController.approveUser(eventPoster.getUsername());
            refreshUI();
        });

        // Reject button
        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(evt -> {
            approvalController.rejectUser(eventPoster.getUsername());
            refreshUI();
        });

        // Add components to the user panel
        userPanel.add(userLabel);
        userPanel.add(Box.createHorizontalGlue());
        userPanel.add(approveButton);
        userPanel.add(Box.createHorizontalStrut(10)); // Space between buttons
        userPanel.add(rejectButton);

        return userPanel;
    }

    private void refreshUI() {
        initializeRequests();
    }

    public void setApprovalController(AdminApprovalController approvalController) {
        this.approvalController = approvalController;
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public static void main(String[] args) {
        // Dummy data for testing
        AdminApprovalState adminApprovalState = new AdminApprovalState();
        adminApprovalState.setUnapprovedUsers(List.of(
                new EventPoster("johndoe", "password123", "TechOrg", "http://example.com", null),
                new EventPoster("janedoe", "password456", "ArtClub", "http://example.org", null)
        ));

        AdminApprovalViewModel adminApprovalViewModel = new AdminApprovalViewModel();
        adminApprovalViewModel.setState(adminApprovalState);

        AdminApprovalController approvalController = new AdminApprovalController(new AdminApprovalInteractor(
                new AdminApprovalPresenter(adminApprovalViewModel, new ViewManagerModel()),
                new InMemoryUserDataAccessObject() // Replace with actual data access implementation
        ));

        SwingUtilities.invokeLater(() -> {
            ApprovalRequests_screen screen = new ApprovalRequests_screen(adminApprovalViewModel);
            screen.setApprovalController(approvalController);
            screen.setVisible(true);
        });
    }
}
