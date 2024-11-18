package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

        // Date panel with placeholder
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanel.add(new JLabel("Date (DD/MM/YYYY):"));
        JTextField dateField = new JTextField("DD/MM/YYYY", 15);
        addPlaceholderEffect(dateField, "DD/MM/YYYY");
        datePanel.add(dateField);
        panel.add(datePanel);

        // Start time panel with placeholder
        JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startTimePanel.add(new JLabel("Start time:"));
        JTextField startTimeField = new JTextField("HH:MM", 15);
        addPlaceholderEffect(startTimeField, "HH:MM");
        startTimePanel.add(startTimeField);
        panel.add(startTimePanel);

        // End time panel with placeholder
        JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endTimePanel.add(new JLabel("End time:"));
        JTextField endTimeField = new JTextField("HH:MM", 15);
        addPlaceholderEffect(endTimeField, "HH:MM");
        endTimePanel.add(endTimeField);
        panel.add(endTimePanel);

        // Location panel
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        locationPanel.add(new JLabel("Location:"));
        locationPanel.add(new JTextField(15));
        panel.add(locationPanel);

        // Tags panel with a JComboBox
        JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tagsPanel.add(new JLabel("Tags (Up to 3):"));
        JComboBox<String> tagsComboBox = new JComboBox<>(new String[]{"Sports", "Drawing", "Environmental"});
        tagsPanel.add(tagsComboBox);
        panel.add(tagsPanel);

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

    // Helper method to add placeholder effect to a JTextField
    private void addPlaceholderEffect(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create the GUI by instantiating the EventCreationScreen class
        new EventPost_screen();
    }
}
