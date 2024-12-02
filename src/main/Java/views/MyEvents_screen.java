package views;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_event.*;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import org.jetbrains.annotations.NotNull;
import entity.Event;
import entity.EventPoster;
import use_case.delete_event.DeleteEventDataAccessInterface;
import use_case.delete_event.DeleteEventInteractor;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

public class MyEvents_screen extends JFrame implements PropertyChangeListener {
    private static final String VIEW_NAME = "My Events";

    private MyEventsViewModel myEventsViewModel;
    private LoggedInViewModel loggedInViewModel;
    private DeleteEventController deleteEventController;

    private final JPanel eventsPanel;
    private final JButton addEventButton;
    private final JButton backButton;

    public MyEvents_screen(MyEventsViewModel myEventsViewModel, LoggedInViewModel loggedInViewModel) {
        this.myEventsViewModel = myEventsViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.myEventsViewModel.addPropertyChangeListener(this);
        this.loggedInViewModel.addPropertyChangeListener(this);

        setTitle(VIEW_NAME);
        setupFrame();

        // For title at the top
        JLabel titleLabel = new JLabel(VIEW_NAME, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons (Back and Add Event)
        backButton = new JButton("Back");
        addEventButton = new JButton("Add Event");

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center align buttons with spacing
        buttonPanel.add(backButton);
        buttonPanel.add(addEventButton);

        // Events panel with BoxLayout for vertical stacking
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        // Add a scroll pane to handle large number of events
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Initialize events and add panels to the frame
        initializeEvents();

        // Set the layout for the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH); // Title at the top
        add(scrollPane, BorderLayout.CENTER); // Event list
        add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom

        addBackButtonListener(loggedInViewModel);
        addAddEventButtonListener(loggedInViewModel);
    }

    /**
     * Add back button action listener.
     * @param loggedInViewModel the loggedInViewModel
     */
    private void addAddEventButtonListener(LoggedInViewModel loggedInViewModel) {
        addEventButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(addEventButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        // add event controller to switch to add event screen
                    }
                }
        );
    }

    /**
     * Add the add Event button action listener.
     * @param loggedInViewModel the loggedInViewModel
     */
    private void addBackButtonListener(LoggedInViewModel loggedInViewModel) {
        backButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        // home screen controller
                    }
                }
        );
    }

    /**
     * Set up the frame settings for this view.
     */
    private void setupFrame() {
        // Get screen insets (taskbar size)
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskbarHeight = screenInsets.bottom;

        // Adjust window size to avoid taskbar overlap
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height - taskbarHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
    }

    /**
     * Initialize the events from the logged in view.
     */
    private void initializeEvents() {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        List<Event> events = new ArrayList<>(loggedInState.getEvents().values());

        MyEventsState myEventsState = myEventsViewModel.getState();
        myEventsState.setEvents(events);
        myEventsViewModel.setState(myEventsState);

        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
    }

    private void loadEvents(MyEventsState myEventsState) {
        List<Event> events = myEventsState.getEvents();
        eventsPanel.removeAll();

        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
        SwingUtilities.invokeLater(() -> {
            eventsPanel.revalidate();
            eventsPanel.repaint();
        });
    }

    private JPanel createEventPanel(Event event) {
        // Create a panel with BoxLayout for horizontal arrangement of details and button
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.X_AXIS)); // Horizontal layout
        eventPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding for each event panel

        // Panel for the event details
        JPanel detailsContentPanel = getEventPanel(event);
        eventPanel.add(detailsContentPanel);

        // Create the delete button with better spacing
        JButton delete = getjButton(event);

        // Add delete button beside the event details with some margin between
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.X_AXIS));
        deletePanel.add(Box.createHorizontalStrut(20)); // Space between event details and button
        deletePanel.add(delete);
        eventPanel.add(deletePanel);

        return eventPanel;
    }

    @NotNull
    private JButton getjButton(Event event) {
        JButton delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(100, 40)); // Set size for consistency
        delete.addActionListener(evt -> {
            if (evt.getSource().equals(delete)) {
                final LoggedInState currentLoggedInState = loggedInViewModel.getState();
                this.deleteEventController.deleteEvent(currentLoggedInState.getUsername(), event);
                loadEvents(myEventsViewModel.getState());
            }
        });
        return delete;
    }

    @NotNull
    private static JPanel getEventPanel(Event event) {
        JPanel detailsContentPanel = new JPanel();
        detailsContentPanel.setLayout(new BoxLayout(detailsContentPanel, BoxLayout.Y_AXIS));

        // Create labels for the event details
        JLabel nameLabel = new JLabel("Event: " + event.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel descriptionLabel = new JLabel("Description: " + event.getDescription());
        JLabel locationLabel = new JLabel("Location: " + event.getLocation());
        JLabel startLabel = new JLabel("Start: " + event.getStart());
        JLabel endLabel = new JLabel("End: " + event.getEnd());

        // Add labels to the panel with padding
        detailsContentPanel.add(nameLabel);
        detailsContentPanel.add(Box.createVerticalStrut(5)); // Space between labels
        detailsContentPanel.add(descriptionLabel);
        detailsContentPanel.add(Box.createVerticalStrut(5)); // Space between labels
        detailsContentPanel.add(locationLabel);
        detailsContentPanel.add(Box.createVerticalStrut(5)); // Space between labels
        detailsContentPanel.add(startLabel);
        detailsContentPanel.add(Box.createVerticalStrut(5)); // Space between labels
        detailsContentPanel.add(endLabel);

        return detailsContentPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final MyEventsState state = (MyEventsState) evt.getNewValue();
            loadEvents(state);
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    public void setDeleteEventController(DeleteEventController deleteEventController) {
        this.deleteEventController = deleteEventController;
    }

    public static void main(String[] args) {
        // Sample event data
        Event event1 = new Event("Tech Conference", "A conference about tech innovations.", "New York",
                LocalDateTime.of(2024, 12, 1, 9, 0), LocalDateTime.of(2024, 12, 1, 17, 0), List.of("tag1", "tag2"));

        Event event2 = new Event("Education Seminar", "A seminar on modern education techniques.", "Los Angeles",
                LocalDateTime.of(2024, 12, 5, 10, 0), LocalDateTime.of(2024, 12, 5, 16, 0), List.of("tag1", "tag2", "tag3"));

        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put(event1.getName(), event1);
        eventMap.put(event2.getName(), event2);

        EventPoster eventPoster = new EventPoster("john_doe", "password123", "TechCorp", "http://sop.link", eventMap);

        LoggedInState loggedInState = new LoggedInState();
        if (eventPoster.getEvents() != null) {
            loggedInState.setEvents(eventPoster.getEvents());
        } else {
            loggedInState.setEvents(new HashMap<>());
        }
        loggedInState.setUsername(eventPoster.getUsername());
        loggedInState.setPassword(eventPoster.getPassword());

        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        loggedInViewModel.setState(loggedInState);

        MyEventsViewModel myEventsViewModel = new MyEventsViewModel();
        DeleteEventDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.addAccount(eventPoster);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DeleteEventPresenter deleteEventPresenter = new DeleteEventPresenter(viewManagerModel, myEventsViewModel);
        DeleteEventInteractor deleteEventInteractor = new DeleteEventInteractor(dataAccess, deleteEventPresenter);
        DeleteEventController controller = new DeleteEventController(deleteEventInteractor);

        // Initialize MyEvents_screen and inject the DeleteEventController using setter
        MyEvents_screen myEventsScreen = new MyEvents_screen(myEventsViewModel, loggedInViewModel);
        myEventsScreen.setDeleteEventController(controller);

        // Show the frame
        SwingUtilities.invokeLater(() -> myEventsScreen.setVisible(true));
    }
}
