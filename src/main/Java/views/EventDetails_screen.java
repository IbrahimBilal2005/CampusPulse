package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventDetails_screen {

    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Event Details Screen"); // Create Frame and have it close when the x is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the screen size to a quarter the users screen and center it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel bannerLabel = new JLabel("CampusPulse");
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField searchField = new JTextField("Search Events...");
        searchField.setPreferredSize(new Dimension(150, 30));

        JButton profileButton = new JButton(new ImageIcon("path/to/profile/icon.png"));
        profileButton.setPreferredSize(new Dimension(40, 40));

        JSeparator separator = new JSeparator();
        separator.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the separator
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(Color.BLACK);

        bannerPanel.add(bannerLabel, BorderLayout.WEST);
        bannerPanel.add(searchField, BorderLayout.CENTER);
        bannerPanel.add(profileButton, BorderLayout.EAST);
        bannerPanel.add(separator, BorderLayout.SOUTH);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Event name label
        JLabel eventNameLabel = new JLabel("Event Name");
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        detailsPanel.add(new JLabel("Date:"));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10)); // Add space

        detailsPanel.add(new JLabel("Location:"));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10)); // Add space

        detailsPanel.add(new JLabel("Time:"));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10)); // Add space

        detailsPanel.add(new JLabel("Link:"));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10)); // Add space

        detailsPanel.add(new JLabel("Add map stuff etc."));
        // Image panel (right side)
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 300));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border
        JLabel imageLabel = new JLabel("image/default image", SwingConstants.CENTER);
        imagePanel.add(imageLabel);

        // Add components to the main panel
        main_panel.add(eventNameLabel, BorderLayout.NORTH);
        main_panel.add(detailsPanel, BorderLayout.CENTER);
        main_panel.add(imagePanel, BorderLayout.EAST);
        
        // Add panels to the frame
        frame.add(bannerPanel, BorderLayout.NORTH);
        frame.add(main_panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
