package views;

import interface_adapter.event_details.EventDetailsController;
import interface_adapter.event_details.EventDetailsViewModel;
import entity.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List; //TO BE REMOVED ONCE FILE COMPLETED
import java.util.ArrayList; //TO BE REMOVED ONCE FILE COMPLETED

public class EventDetails_screen {

    private EventDetailsController controller;

    public EventDetails_screen(EventDetailsViewModel view, Event event) {

    //public static void main(String[] args) {
//        //TESTING CODE
//        EventDetailsViewModel view = new EventDetailsViewModel();
//        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1,12, 15);
//        List<String> stringList = new ArrayList<>();
//        stringList.add("Monday");
//        Event event = new Event("test name", "This is description", "22 Shorten Place", dateTime, dateTime, stringList);
//

        // Create a new frame
        JFrame frame = new JFrame("Event Details Screen"); // Create Frame and have it close when the x is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the screen size to a quarter the users screen and center it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel bannerLabel = new JLabel(view.TITLE);
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
        JLabel eventNameLabel = new JLabel(event.getName());
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align elements to the left
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setLineWrap(true); // Enable line wrapping
        descriptionArea.setWrapStyleWord(true); // Wrap words properly
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(600, 100));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Align description to the left
        detailsPanel.add(scrollPane);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        detailsPanel.add(new JLabel(view.DATE + dateConvert(event.getStart()) + " - " + dateConvert(event.getEnd())));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        detailsPanel.add(new JLabel(view.LOCATION + event.getLocation()));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        detailsPanel.add(new JLabel(view.TIME + timeConvert(event.getStart()) + " - " + timeConvert(event.getEnd())));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        detailsPanel.add(new JLabel(view.TAGS + event.getTags().toString()));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        JButton backButton = new JButton(view.BACK);
        backButton.setPreferredSize(new Dimension(80, 30));
        detailsPanel.add(backButton);

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 300));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel imageLabel = new JLabel("image/default image", SwingConstants.CENTER);
        imagePanel.add(imageLabel);

        // Add components to the main panel
        main_panel.add(detailsPanel, BorderLayout.CENTER);
        main_panel.add(imagePanel, BorderLayout.EAST);
        //main_panel.add(backButtonPanel, BorderLayout.NORTH);
        main_panel.add(eventNameLabel, BorderLayout.NORTH); // Add the event name at the top

        // Add panels to the frame
        frame.add(bannerPanel, BorderLayout.NORTH);
        frame.add(main_panel, BorderLayout.CENTER);
        frame.setVisible(true);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(backButton)) {
                            controller.execute();
                        }
                    }
                }
        );
    }

    private static String dateConvert(LocalDateTime date) {
        return date.getMonth().toString().charAt(0) + date.getMonth().toString().toLowerCase().substring(1) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    private static String timeConvert(LocalDateTime time) {
        if (time.getMinute() < 10) {
            return time.getHour() + ":0" + time.getMinute();
        }
        return time.getHour() + ":" + time.getMinute();
    }

}
