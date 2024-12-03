package views;

import data_access.GeoapifyRequest;
import interface_adapter.event_details.EventDetailsController;
import interface_adapter.event_details.EventDetailsViewModel;
import entity.CustomImagePanel;
import entity.Event;
import interface_adapter.filter.FilterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * The view for when the user is logging into their account
 */
public class EventDetails_screen extends JFrame {

    private EventDetailsController eventDetailsController;

    public EventDetails_screen(EventDetailsViewModel view, Event event) {
        // Create a new frame
        JFrame frame = new JFrame("Event Details Screen"); // Create Frame and have it close when the x is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the screen size to a quarter the users screen and center it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);

        // Create a panel to contain the elements
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Event name label
        JLabel eventNameLabel = new JLabel(event.getName());
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // A panel created so that the details can be placed on the left side
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align elements to the left
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Due to the size of a possible description, put it within a JTextArea so that you can allow for line wrapping
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setLineWrap(true); // Enable line wrapping
        descriptionArea.setWrapStyleWord(true); // Wrap words properly
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Allows for the description area to have a scroll bar if needed
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(600, 75));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Align description to the left
        detailsPanel.add(scrollPane);

        // Date label by using the dateConvert function and the event info
        detailsPanel.add(new JLabel(view.DATE + dateConvert(event.getStart()) + " - " + dateConvert(event.getEnd())));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        // Location label by using the event data
        detailsPanel.add(new JLabel(view.LOCATION + event.getLocation()));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        // Time label by using the timeConvert function and the event info
        detailsPanel.add(new JLabel(view.TIME + timeConvert(event.getStart()) + " - " + timeConvert(event.getEnd())));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        // Tags label by using the event data
        detailsPanel.add(new JLabel(view.TAGS + event.getTags().toString()));
        detailsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        detailsPanel.add(Box.createVerticalStrut(10));

        // Creates a button that takes the user back to the home screen
        JButton backButton = new JButton(view.BACK);
        backButton.setPreferredSize(new Dimension(80, 30));
        detailsPanel.add(backButton);

        // Make an image panel with set dimensions to put the location image into
        CustomImagePanel imagePanel = new CustomImagePanel();
        imagePanel.setPreferredSize(new Dimension(300, 300));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Use a try-catch system as well the custom image panel class and mapLoc function to draw the function into the image panel
        try {
            BufferedImage map = mapLoc(event.getLocation());
            imagePanel.setMap(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Add components to the main panel
        main_panel.add(detailsPanel, BorderLayout.CENTER); // Add the details and back button to the left side
        main_panel.add(imagePanel, BorderLayout.EAST); // Add the map location to the right side
        main_panel.add(eventNameLabel, BorderLayout.NORTH); // Add the event name at the top

        // Add panel to the frame
        frame.add(main_panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Action listener for a back button that takes the user back to the home screen
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(backButton)) {
                            eventDetailsController.execute();
                        }
                    }
                }
        );
    }

    // Take the month, day and year and structure them into viewable way
    private static String dateConvert(LocalDateTime date) {
        return date.getMonth().toString().charAt(0) + date.getMonth().toString().toLowerCase().substring(1) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    // Take the hour and minute from the LocalDateTime and convert it into a readable format
    private static String timeConvert(LocalDateTime time) {
        if (time.getMinute() < 10) {
            return time.getHour() + ":0" + time.getMinute();
        }
        return time.getHour() + ":" + time.getMinute();
    }

    // Image panels in JSWING use buffered image as an input so convert the location into a buffered image
    private static BufferedImage mapLoc(String loc) throws Exception {
        GeoapifyRequest locImage = new GeoapifyRequest("00b11d0dc6c34c75bb7f719c3d745872");
        return locImage.getMapImage(loc);
    }

    public String getViewName() {
        return "Event Poster Signup";
    }

    public void setEventDetailsController(EventDetailsController eventDetailsController) {
        this.eventDetailsController = eventDetailsController;
    }

}
