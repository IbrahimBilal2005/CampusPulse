package views;

import javax.swing.*;
import java.awt.*;


public class EventPost_screen {
    public EventPost_screen() {

        JFrame frame = new JFrame("CampusPulse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Main panel with BoxLayout (Y_AXIS) to arrange components vertically
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("Create Event", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20)); // Spacer

        // Event Name panel
        JPanel eventNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        eventNamePanel.add(new JLabel("Event Name:"));
        eventNamePanel.add(new JTextField(15));
        panel.add(eventNamePanel);

        // Event Description panel (multiline JTextArea)
        JPanel eventDescriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        eventDescriptionPanel.add(new JLabel("Event Description:"));
        JTextArea descriptionArea = new JTextArea(3, 15);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        eventDescriptionPanel.add(scrollPane);
        panel.add(eventDescriptionPanel);

        // Start DateTime panel
        JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startTimePanel.add(new JLabel("Start Date & Time (24 hr) (dd-MM-yyyy HH:MM):"));
        JTextField startTimeField = new JTextField(15);
        startTimePanel.add(startTimeField);
        panel.add(startTimePanel);

        // End DateTime panel
        JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endTimePanel.add(new JLabel("End Date & Time  (24 hr) (dd-MM-yyyy HH:MM):"));
        JTextField endTimeField = new JTextField(15);
        endTimePanel.add(endTimeField);
        panel.add(endTimePanel);

        // Location panel
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        locationPanel.add(new JLabel("Location:"));
        locationPanel.add(new JTextField(15));
        panel.add(locationPanel);

        // Tags panel with a JComboBox
        JPanel tag1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tag1Panel.add(new JLabel("Tag 1:"));
        JComboBox<String> tagsComboBox = new JComboBox<>(new String[]{"Sports", "Drawing", "Environmental"});
        tag1Panel.add(tagsComboBox);
        panel.add(tag1Panel);

        JPanel tag2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tag2Panel.add(new JLabel("Tag 2:"));
        JComboBox<String> tags2ComboBox = new JComboBox<>(new String[]{"Sports", "Drawing", "Environmental"});
        tag2Panel.add(tags2ComboBox);
        panel.add(tag2Panel);

        panel.add(Box.createVerticalStrut(20)); // Spacer

        // Buttons panel for Post Event and Cancel buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton postButton = new JButton("Post Event");
        JButton cancelButton = new JButton("Cancel");

        buttonsPanel.add(postButton);
        buttonsPanel.add(cancelButton);
        panel.add(buttonsPanel);

        // Add main panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create the GUI by instantiating the EventCreationScreen class
        new EventPost_screen();
    }
}
