package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ActiveEvents_screen extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public ActiveEvents_screen() {
        // Frame settings
        setTitle("CampusPulse - Active Events");
        setSize(screenSize.width,screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton returnButton = new JButton("Return");
        JLabel titleLabel = new JLabel("CampusPulse", SwingConstants.CENTER);

        // Load and resize profile icon
        JLabel profileIcon = new JLabel();
        profileIcon.setIcon(resizeImage("src/main/Java/data_access/uoftpic.jpeg", 50, 50)); // Adjust size as needed

        headerPanel.add(returnButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(profileIcon, BorderLayout.EAST);

        // Events list panel
        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        // Dummy events
        for (int i = 1; i <= 5; i++) {
            eventsPanel.add(createEventPanel("Event " + i, "Description of Event " + i, "Location " + i, "Date " + i, "path-to-file" + i + ".jpg"));
        }

        // Scroll pane for events list
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);
    }

    private static ImageIcon resizeImage(String filePath, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JPanel createEventPanel(String eventName, String description, String location, String date, String imagePath) {
        JPanel eventPanel = new JPanel(new BorderLayout()); // Use BorderLayout for side-by-side alignment

        // Event image
        JLabel eventImageLabel = new JLabel(resizeImage("src/main/Java/data_access/uoftpic.jpeg", screenSize.width/5, screenSize.height/5)); // Adjust event image size as needed

        // Event details
        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS)); // Vertical layout for details
        eventDetailsPanel.add(new JLabel("Event: " + eventName));
        eventDetailsPanel.add(new JLabel("Description: " + description));
        eventDetailsPanel.add(new JLabel("Location: " + location));
        eventDetailsPanel.add(new JLabel("Date: " + date));

        // Event buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");
        buttons.add(deleteButton);
        buttons.add(editButton);


        // Add image to the left and details to the center
        eventPanel.add(eventImageLabel, BorderLayout.WEST);
        eventPanel.add(eventDetailsPanel, BorderLayout.CENTER);
        eventPanel.add(buttons, BorderLayout.SOUTH);

        return eventPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ActiveEvents_screen().setVisible(true));
    }
}
