package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class ActiveEvents_screen extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel eventsPanel; // Store the eventsPanel as an instance variable
    private ArrayList<JPanel> eventPanels; // Store each event panel for easy removal/editing

    public ActiveEvents_screen() {
        // Initialize the event panels list
        eventPanels = new ArrayList<>();

        // Frame settings
        setTitle("CampusPulse - Active Events");
        setSize(screenSize.width, screenSize.height);
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
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        // Dummy events
        for (int i = 1; i <= 5; i++) {
            JPanel eventPanel = createEventPanel("Event " + i, "Description of Event " + i, "Location " + i, "Date " + i, "path-to-file" + i + ".jpg");
            eventPanels.add(eventPanel); // Add to list for future reference
            eventsPanel.add(eventPanel);
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
        eventPanel.setSize(new Dimension(40, 100));

        // Event image
        JLabel eventImageLabel = new JLabel(resizeImage("src/main/Java/data_access/uoftpic.jpeg", screenSize.width / 10, screenSize.height / 10));

        // Event details with centered alignment
        JPanel eventDetailsPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        JPanel detailsContentPanel = new JPanel();
        detailsContentPanel.setLayout(new BoxLayout(detailsContentPanel, BoxLayout.Y_AXIS)); // Vertical layout for details

        // Set larger font for readability
        Font font = new Font("Arial", Font.PLAIN, 14);

        JLabel nameLabel = new JLabel("Event: " + eventName);
        nameLabel.setFont(font);
        JLabel descriptionLabel = new JLabel("Description: " + description);
        descriptionLabel.setFont(font);
        JLabel locationLabel = new JLabel("Location: " + location);
        locationLabel.setFont(font);
        JLabel dateLabel = new JLabel("Date: " + date);
        dateLabel.setFont(font);

        detailsContentPanel.add(nameLabel);
        detailsContentPanel.add(descriptionLabel);
        detailsContentPanel.add(locationLabel);
        detailsContentPanel.add(dateLabel);

        // Center the detailsContentPanel within eventDetailsPanel
        eventDetailsPanel.add(detailsContentPanel);

        // Event buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align buttons to the right
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");

        // Add functionality to delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventsPanel.remove(eventPanel); // Remove the specific event panel
                eventPanels.remove(eventPanel); // Remove from the list
                eventsPanel.revalidate();
                eventsPanel.repaint();
            }
        });

        // Add functionality to edit button (this is a placeholder action)
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Edit functionality for " + eventName + " will be implemented.");
            }
        });

        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);

        // Add image to the left, details in the center with vertical alignment, and buttons at the bottom
        eventPanel.add(eventImageLabel, BorderLayout.WEST);
        eventPanel.add(eventDetailsPanel);
        eventPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return eventPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ActiveEvents_screen().setVisible(true));
    }
}
