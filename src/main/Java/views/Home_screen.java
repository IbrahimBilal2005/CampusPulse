package views;

import data_access.EventDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchDataAccessInterface;
import entity.Event;
import use_case.search.SearchInteractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Home_screen extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton myEventsButton;
    private JTextField searchField;
    private JPanel eventsPanel;
    private SearchController searchController;
    private SearchViewModel searchViewModel;

    public Home_screen(SearchViewModel searchViewModel, SearchController searchController) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;

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
        searchField = new JTextField(20);
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
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        // Scroll pane for events list
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Add search functionality
        searchField.addActionListener(e -> {
            String query = searchField.getText();
            searchController.search(query); // Trigger the search when the user types in the search field
            updateEventsList(searchViewModel.getState().getResults());
        });

        // Listen for changes to the search results
        searchViewModel.addPropertyChangeListener(evt -> {
            if ("results".equals(evt.getPropertyName())) {
                // Update the events list based on new results
                updateEventsList(searchViewModel.getState().getResults());
            }
        });

        // Trigger an initial empty search to display all events
        searchController.search("");
        for (Event event : searchViewModel.getState().getResults()) {
            eventsPanel.add(createEventPanel(event));
            eventsPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        }
    }

    private void updateEventsList(List<Event> events) {
        eventsPanel.removeAll(); // Clear existing components
        if (events.isEmpty()) {
            eventsPanel.add(new JLabel("No events found"));
        } else {
            for (Event event : events) {
                eventsPanel.add(createEventPanel(event)); // Add new event panels
                eventsPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Add spacing
            }
        }
        eventsPanel.revalidate(); // Refresh layout
        eventsPanel.repaint(); // Redraw the panel
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Event details
        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));

        JLabel eventNameLabel = new JLabel(event.getName());
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        eventDetailsPanel.add(eventNameLabel);

        JLabel eventDescriptionLabel = new JLabel("Description: " + event.getDescription());
        eventDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        eventDetailsPanel.add(eventDescriptionLabel);

        JLabel eventLocationLabel = new JLabel("Location: " + event.getLocation());
        eventLocationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        eventDetailsPanel.add(eventLocationLabel);

        JLabel eventDateLabel = new JLabel("Date: " + event.getStart().toString());
        eventDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        eventDetailsPanel.add(eventDateLabel);

        eventPanel.add(eventDetailsPanel, BorderLayout.CENTER);

        return eventPanel;
    }

    private boolean checkIfEventPoster() {
        // Implement logic to determine if the user is an event poster
        return true; // Replace with actual condition
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

    public static void main(String[] args) {
        SearchDataAccessInterface dataAccess = new EventDAO();
        SearchViewModel viewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchPresenter presenter = new SearchPresenter(viewModel, viewManagerModel);
        SearchInteractor interactor = new SearchInteractor(dataAccess, presenter);
        SearchController controller = new SearchController(interactor);

        SwingUtilities.invokeLater(() -> new Home_screen(viewModel, controller).setVisible(true));
    }
}
