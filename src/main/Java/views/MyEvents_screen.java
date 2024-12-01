package views;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import java.util.List;

import data_access.EventDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_event.DeleteEventController;
import interface_adapter.delete_event.MyEventsState;
import interface_adapter.delete_event.MyEventsViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import org.jetbrains.annotations.NotNull;

//Testing imports. Not apart of logic:
import entity.Event;
import entity.EventPoster;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;


public class MyEvents_screen extends JFrame implements PropertyChangeListener {
    private MyEventsViewModel myEventsViewModel;
    private LoggedInViewModel loggedInViewModel;

    private DeleteEventController deleteEventController;
    // add the AddEventController here, and action listener to new event button

    private JPanel eventsPanel;
    private final JButton addEventButton;

    public MyEvents_screen(MyEventsViewModel myEventsViewModel, LoggedInViewModel loggedInViewModel) {
        this.myEventsViewModel = myEventsViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.myEventsViewModel.addPropertyChangeListener(this);
        this.loggedInViewModel.addPropertyChangeListener(this);


        final JLabel title = new JLabel("My Events");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel addEventPanel = new JPanel();
        addEventButton = new JButton("Add Event");
        addEventPanel.add(addEventButton);

        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        initializeEvents();
    }

    private void initializeEvents() {
        eventsPanel.removeAll();
        final LoggedInState loggedInState = new LoggedInState();
        List<Event> events = new ArrayList<>(loggedInState.getEvents().values());
        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private void loadEvents() {
        eventsPanel.removeAll();
        final MyEventsState myEventsState = new MyEventsState();
        List<Event> events = new ArrayList<>(myEventsState.getEvents());
        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.add(new JLabel(event.getName()), BorderLayout.CENTER);

        JPanel detailsContentPanel = getEventPanel(event);
        eventPanel.add(detailsContentPanel);

        JButton delete = new JButton("Delete");
        delete.addActionListener(
                evt -> {
                    if (evt.getSource().equals(delete)) {
                        final LoggedInState currentLoggedInState = loggedInViewModel.getState();
                        deleteEventController.deleteEvent(currentLoggedInState.getUsername(), event);
                        loggedInViewModel.setState(currentLoggedInState);
                    }
                }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(delete);
        eventPanel.add(buttonPanel, BorderLayout.SOUTH);

        return eventPanel;
    }

    @NotNull
    private static JPanel getEventPanel(Event event) {
        JPanel detailsContentPanel = new JPanel();
        detailsContentPanel.setLayout(new BoxLayout(detailsContentPanel, BoxLayout.Y_AXIS)); // Vertical layout for details

        JLabel nameLabel = new JLabel("Event: " + event.getName());
        JLabel descriptionLabel = new JLabel("Description: " + event.getDescription());
        JLabel locationLabel = new JLabel("Location: " + event.getLocation());
        JLabel startLabel = new JLabel("Start: " + event.getStart());
        JLabel endLabel = new JLabel("End: " + event.getEnd());

        detailsContentPanel.add(nameLabel);
        detailsContentPanel.add(descriptionLabel);
        detailsContentPanel.add(locationLabel);
        detailsContentPanel.add(startLabel);
        detailsContentPanel.add(endLabel);
        return detailsContentPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("events".equals(evt.getPropertyName())) {
            // Whenever the events change in the ViewModel, reload them in the view
            loadEvents();
        }
    }

    public static void main(String[] args) {
        // Create an Event instance
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("Education Seminar", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put("event1", event1);
        eventMap.put("event2", event2);

        EventPoster eventPoster = new EventPoster("john_doe", "password123", "TechCorp", "http://sop.link", eventMap);

        // Create LoggedInState and set events
        LoggedInState loggedInState = new LoggedInState();
        loggedInState.setEvents(eventPoster.getEvents());
        loggedInState.setUsername(eventPoster.getUsername());
        loggedInState.setPassword(eventPoster.getPassword());

        // Create LoggedInViewModel and link it with LoggedInState
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        loggedInViewModel.setState(loggedInState); // THIS IS IMPORTANT!

        MyEventsViewModel viewModel = new MyEventsViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Initialize the MyEvents_screen and pass the ViewModel and LoggedInViewModel to it
        SwingUtilities.invokeLater(() -> new MyEvents_screen(viewModel, loggedInViewModel));
    }

}
