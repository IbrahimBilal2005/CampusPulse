package views;

import javax.swing.*;
import java.awt.*;

public class ChangePassword_screen {
    public ChangePassword_screen() {
        // Create the frame
        JFrame frame = new JFrame("CampusPulse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel with BoxLayout (Y_AXIS) to arrange components vertically
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        // Title label
        JLabel titleLabel = new JLabel("Change Password", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align in BoxLayout
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(20)); // Spacer

        // Current password panel
        JPanel currentPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        currentPasswordPanel.add(new JLabel("Current password:"));
        currentPasswordPanel.add(new JPasswordField(15));
        panel.add(currentPasswordPanel);

        // New password panel
        JPanel newPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPasswordPanel.add(new JLabel("New password:"));
        newPasswordPanel.add(new JPasswordField(15));
        panel.add(newPasswordPanel);

        // Confirm new password panel
        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmPasswordPanel.add(new JLabel("Confirm New password:"));
        confirmPasswordPanel.add(new JPasswordField(15));
        panel.add(confirmPasswordPanel);

        panel.add(Box.createVerticalStrut(10)); // Spacer

        // Finish button
        JButton finishButton = new JButton("Finish");
        finishButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align in BoxLayout
        panel.add(finishButton);

        // Add the main panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create the GUI by instantiating the ChangePassword_screen class
        new ChangePassword_screen();
    }
}
