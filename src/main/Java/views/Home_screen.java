package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Home_screen extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton myEventsButton;

    public Home_screen() {
        // Frame settings
        setTitle("CampusPulse - Home");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Title on the left
        JLabel titleLabel = new JLabel("Campus Pulse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Center panel for search bar and filter button
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JTextField searchField = new JTextField(20);
        JButton filterButton = new JButton("Filters");
        filterButton.setPreferredSize(new Dimension(80, 30));

        centerPanel.add(searchField);
        centerPanel.add(filterButton);
        headerPanel.add(centerPanel, BorderLayout.CENTER);

        // Right panel for My Events button and profile icon
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        myEventsButton = new JButton("My Events");
        myEventsButton.setVisible(false);
        myEventsButton.setPreferredSize(new Dimension(120, 30));

        JLabel profileIcon = new JLabel();
        profileIcon.setIcon(resizeImage("src/main/Java/data_access/uoftpic.jpeg", 50, 50));

        rightPanel.add(myEventsButton);
        rightPanel.add(profileIcon);

        headerPanel.add(rightPanel, BorderLayout.EAST);

        // Check if the user is an event poster
        boolean isEventPoster = checkIfEventPoster();
        if (isEventPoster) {
            myEventsButton.setVisible(true);
        }

        // Events list panel
        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        // Dummy events
        for (int i = 1; i <= 5; i++) {
            eventsPanel.add(createEventPanel("Event " + i, "Description of Event " + i, "Location " + i, "Date " + i, "path-to-file" + i + ".jpg"));
            eventsPanel.add(Box.createRigidArea(new Dimension(0, 40)));
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
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Event image
        JLabel eventImageLabel = new JLabel(resizeImage("src/main/Java/data_access/uoftpic.jpeg", 250, 250));

        // Event details
        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));

        JLabel eventNameLabel = new JLabel(eventName);
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 24 * 2));
        eventDetailsPanel.add(eventNameLabel);

        JLabel eventDescriptionLabel = new JLabel("Description: " + description);
        eventDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20 * 2));
        eventDetailsPanel.add(eventDescriptionLabel);

        JLabel eventLocationLabel = new JLabel("Location: " + location);
        eventLocationLabel.setFont(new Font("Arial", Font.PLAIN, 20 * 2));
        eventDetailsPanel.add(eventLocationLabel);

        JLabel eventDateLabel = new JLabel("Date: " + date);
        eventDateLabel.setFont(new Font("Arial", Font.PLAIN, 20 * 2));
        eventDetailsPanel.add(eventDateLabel);

        // Add components to event panel
        eventPanel.add(eventImageLabel, BorderLayout.WEST);
        eventPanel.add(eventDetailsPanel, BorderLayout.CENTER);

        return eventPanel;
    }

    private boolean checkIfEventPoster() {
        // Implement logic to determine if the user is an event poster
        return true; // Replace with actual condition
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home_screen().setVisible(true));
    }
}
