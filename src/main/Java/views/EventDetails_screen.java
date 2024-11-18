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

        frame.add(bannerPanel);
        frame.setVisible(true);

    }
}
