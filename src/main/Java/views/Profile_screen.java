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
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("CampusPulse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Load and resize profile icon
        JLabel profileIcon = new JLabel(new ImageIcon("src/main/Java/data_access/uoftpic.jpeg"));
        profileIcon.setPreferredSize(new Dimension(screenSize.width / 12, screenSize.height / 12));
        headerPanel.add(profileIcon, BorderLayout.EAST);

        // Profile details panel with GridBagLayout
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel helloLabel = new JLabel("Hello, 'Name'!");
        helloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = 2;
        detailsPanel.add(helloLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField("username_here");
        usernameField.setPreferredSize(new Dimension(200, 25));
        usernameField.setEditable(false);

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField("name_here");
        nameField.setPreferredSize(new Dimension(200, 25));
        nameField.setEditable(false);

        JLabel genderLabel = new JLabel("Gender: ");
        JTextField genderField = new JTextField("gender_here");
        genderField.setPreferredSize(new Dimension(200, 25));
        genderField.setEditable(false);

        JLabel interestsLabel = new JLabel("Interests: ");
        JTextField interestsField = new JTextField("interest1, interest2, ...");
        interestsField.setPreferredSize(new Dimension(200, 25));
        interestsField.setEditable(false);

        // Adding components to details panel with consistent layout
        detailsPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        detailsPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        detailsPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        detailsPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        detailsPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        detailsPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        detailsPanel.add(interestsLabel, gbc);
        gbc.gridx = 1;
        detailsPanel.add(interestsField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton changePasswordButton = new JButton("Change Password");
        JButton editInterestsButton = new JButton("Edit Interests");

        buttonPanel.add(changePasswordButton);
        buttonPanel.add(editInterestsButton);

        // Add action listeners for buttons
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

        // Adding panels to main panel with BorderLayout
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
