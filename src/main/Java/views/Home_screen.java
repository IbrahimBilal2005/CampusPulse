package views;

import data_access.EventDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.filter.FilterController;
import interface_adapter.filter.FilterPresenter;
import interface_adapter.filter.FilterViewModel;
import interface_adapter.filter.FilterViewState;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.filter.FilterDataAccessInterface;
import use_case.filter.FilterInteractor;
import use_case.search.SearchDataAccessInterface;
import entity.Event;
import use_case.search.SearchInteractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_screen extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton myEventsButton;
    private JTextField searchField;
    private JPanel eventsPanel;
    private SearchController searchController;
    private SearchViewModel searchViewModel;
    private FilterController filterController;
    private FilterViewModel filterViewModel;

    public Home_screen(SearchViewModel searchViewModel, SearchController searchController, FilterController filterController, FilterViewModel filterViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.filterController = filterController;
        this.filterViewModel = filterViewModel;

        // Frame settings
        setupFrame();

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = createHeaderPanel();

        // Events list panel
        eventsPanel = createEventsPanel();
        JScrollPane scrollPane = new JScrollPane(eventsPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Initialize search functionality
        initializeSearchFunctionality();

        // Trigger an initial empty search to display all events
        triggerInitialSearch();
    }

    private void setupFrame() {
        setTitle("CampusPulse - Home");
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(createTitleLabel(), BorderLayout.WEST);
        headerPanel.add(createCenterPanel(), BorderLayout.CENTER);
        headerPanel.add(createRightPanel(), BorderLayout.EAST);
        return headerPanel;
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Campus Pulse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        return titleLabel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchField = new JTextField(20);
        centerPanel.add(searchField);
        centerPanel.add(createFilterComboBox());
        return centerPanel;
    }

    private JComboBox<String> createFilterComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Filters"});
        comboBox.setPrototypeDisplayValue("Filters");
        comboBox.setEditable(false);

        // Map to hold filter criteria
        Map<String, Object> filterCriteria = new HashMap<>();

        comboBox.addActionListener(e -> {
            if (comboBox.isPopupVisible()) {
                JPopupMenu popup = createFilterPopup(filterCriteria);
                Component comp = (Component) e.getSource();
                popup.show(comp, 0, comp.getHeight());
            }
        });

        return comboBox;
    }

    private JPopupMenu createFilterPopup(Map<String, Object> filterCriteria) {
        JPopupMenu popup = new JPopupMenu();
        JPanel filterPanel = createFilterPanel(filterCriteria);
        JScrollPane scrollfilter = new JScrollPane(filterPanel);
        scrollfilter.setPreferredSize(new Dimension(250, 200));
        popup.setLayout(new BorderLayout());
        popup.add(scrollfilter, BorderLayout.CENTER);
        return popup;
    }

    private JPanel createFilterPanel(Map<String, Object> filterCriteria) {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

        // Duration slider
        filterPanel.add(new JLabel("Duration (Hours):"));
        JSlider durationSlider = new JSlider(1, 4);
        durationSlider.setMajorTickSpacing(1);
        durationSlider.setPaintTicks(true);
        durationSlider.setPaintLabels(true);
        durationSlider.addChangeListener(e -> filterCriteria.put("duration", durationSlider.getValue()));
        filterPanel.add(durationSlider);

        // Location text field
        filterPanel.add(new JLabel("Location:"));
        JTextField locationField = new JTextField();
        locationField.setMaximumSize(new Dimension(200, 25));
        locationField.addActionListener(e -> filterCriteria.put("location", locationField.getText()));
        filterPanel.add(locationField);

        // Tags checkboxes
        filterPanel.add(new JLabel("Categories:"));
        String[] categories = {"Sports", "Drawing", "Environmental"};
        List<String> selectedTags = new ArrayList<>();
        for (String category : categories) {
            JCheckBox checkBox = new JCheckBox(category);
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    selectedTags.add(category);
                } else {
                    selectedTags.remove(category);
                }
                filterCriteria.put("tags", new ArrayList<>(selectedTags));
            });
            filterPanel.add(checkBox);
        }

        // Apply Button
        JButton applyButton = new JButton("Apply Filters");
        applyButton.addActionListener(e -> {
            System.out.println("Filters Applied: " + filterCriteria); // Debugging
            filterCriteria.put("query", searchField.getText());
            filterController.executeFilter(filterCriteria);
            updateEventsList(filterViewModel.getState().getFilteredEvents());// Call a method in the controller
        });
        JButton resetButton = new JButton("Reset Filters");
        resetButton.addActionListener(e -> {
            System.out.println("Filters Applied: " + filterCriteria);
            filterCriteria.put("duration", null);
            filterCriteria.put("location", null);
            filterCriteria.put("tags", null);
            filterCriteria.put("query", searchField.getText());
            System.out.println("Filters Applied: " + filterCriteria); // Debugging
            filterController.executeFilter(filterCriteria); // Call a method in the controller
            updateEventsList(filterViewModel.getState().getFilteredEvents());
        });
        filterPanel.add(applyButton);
        filterPanel.add(resetButton);

        return filterPanel;
    }


    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        myEventsButton = new JButton("My Events");
        myEventsButton.setVisible(false);
        myEventsButton.setPreferredSize(new Dimension(120, 30));

        JLabel profileIcon = new JLabel();
        profileIcon.setIcon(resizeImage("src/main/Java/data_access/uoftpic.jpeg", 50, 50));

        rightPanel.add(myEventsButton);
        rightPanel.add(profileIcon);

        if (checkIfEventPoster()) {
            myEventsButton.setVisible(true);
        }

        return rightPanel;
    }

    private JPanel createEventsPanel() {
        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        return eventsPanel;
    }

    private void initializeSearchFunctionality() {
        searchField.addActionListener(e -> {
            String query = searchField.getText();
            searchController.search(query);
            updateEventsList(searchViewModel.getState().getResults());
        });

        searchViewModel.addPropertyChangeListener(evt -> {
            if ("results".equals(evt.getPropertyName())) {
                updateEventsList(searchViewModel.getState().getResults());
            }
        });
    }

    private void triggerInitialSearch() {
        searchController.search("");
        HashMap filterCriteria = new HashMap<>();
        filterCriteria.put("duration", null);
        filterCriteria.put("location", null);
        filterCriteria.put("tags", null);
        filterCriteria.put("query", searchField.getText());
        filterController.executeFilter(filterCriteria);
        updateEventsList(searchViewModel.getState().getResults());
    }

    private void updateEventsList(List<Event> events) {
        eventsPanel.removeAll();
        if (events.isEmpty()) {
            eventsPanel.add(new JLabel("No events found"));
        } else {
            for (Event event : events) {
                eventsPanel.add(createEventPanel(event));
                eventsPanel.add(Box.createRigidArea(new Dimension(0, 40)));
            }
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));

        eventDetailsPanel.add(createLabel(event.getName(), new Font("Arial", Font.BOLD, 24)));
        eventDetailsPanel.add(createLabel("Description: " + event.getDescription(), new Font("Arial", Font.PLAIN, 20)));
        eventDetailsPanel.add(createLabel("Location: " + event.getLocation(), new Font("Arial", Font.PLAIN, 20)));
        eventDetailsPanel.add(createLabel("Date: " + event.getStart().toString(), new Font("Arial", Font.PLAIN, 20)));

        eventPanel.add(eventDetailsPanel, BorderLayout.CENTER);
        return eventPanel;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
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
        EventDAO dataAccess = new EventDAO();
        FilterDataAccessInterface filterdao = dataAccess;
        SearchViewModel viewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchPresenter presenter = new SearchPresenter(viewModel, viewManagerModel);
        SearchInteractor interactor = new SearchInteractor(dataAccess, presenter);
        SearchController controller = new SearchController(interactor);
        FilterViewModel filterViewModel = new FilterViewModel();
        FilterPresenter filterPresenter = new FilterPresenter(filterViewModel, viewManagerModel);
        FilterInteractor filterInteractor = new FilterInteractor(dataAccess, filterPresenter);
        FilterController filterController = new FilterController(filterInteractor);

        SwingUtilities.invokeLater(() -> new Home_screen(viewModel, controller, filterController, filterViewModel).setVisible(true));
    }
}
