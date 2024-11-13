package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile_screen extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Profile_screen() {
        // Frame settings
        setTitle("CampusPulse - Profile");
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("CampusPulse");

        // Load and resize profile icon
        JLabel profileIcon = new JLabel(new ImageIcon("src/main/Java/data_access/uoftpic.jpeg")); // Adjust image path
        profileIcon.setPreferredSize(new Dimension(screenSize.width/15, screenSize.height/15)); // Resize profile icon
        headerPanel.add(titleLabel);
        headerPanel.add(profileIcon, BorderLayout.EAST);

        // Profile details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel helloLabel = new JLabel("Hello, 'Name'!");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel genderLabel = new JLabel("Gender: ");
        JLabel interestsLabel = new JLabel("Interests: ");

        JTextField usernameField = new JTextField("username_here");
        JTextField nameField = new JTextField("name_here");
        JTextField genderField = new JTextField("gender_here");
        JTextField interestsField = new JTextField("interest1, interest2, ...");

        int fieldWidth = 200;
        int fieldHeight = 25;

        usernameField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        nameField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        genderField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        interestsField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));

        // Disable fields to prevent editing
        usernameField.setEditable(false);
        nameField.setEditable(false);
        genderField.setEditable(false);
        interestsField.setEditable(false);

        mainPanel.add(helloLabel);
        detailsPanel.add(Box.createVerticalStrut(100)); // Spacer
        detailsPanel.add(usernameLabel);
        detailsPanel.add(usernameField);
        detailsPanel.add(nameLabel);
        detailsPanel.add(nameField);
        detailsPanel.add(genderLabel);
        detailsPanel.add(genderField);
        detailsPanel.add(interestsLabel);
        detailsPanel.add(interestsField);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton changePasswordButton = new JButton("Change Password");
        JButton editInterestsButton = new JButton("Edit Interests");

        buttonPanel.add(changePasswordButton);
        buttonPanel.add(editInterestsButton);

        // Action listeners
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to change password
            }
        });

        editInterestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to edit interests
            }
        });

        // Adding components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Profile_screen profileScreen = new Profile_screen();
            profileScreen.setVisible(true);
        });
    }
}
