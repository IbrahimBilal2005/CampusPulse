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
import java.util.ArrayList;
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
        centerPanel.add(createFilterButton());
        return centerPanel;
    }

    private JComboBox<String> createFilterComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Select Items"});
        comboBox.setPrototypeDisplayValue("Select multiple items");
        comboBox.setEditable(false);

        comboBox.addActionListener(e -> {
            if (comboBox.isPopupVisible()) {
                JPopupMenu popup = createFilterPopup();
                Component comp = (Component) e.getSource();
                popup.show(comp, 0, comp.getHeight());
            }
        });

        return comboBox;
    }

    private JPopupMenu createFilterPopup() {
        JPopupMenu popup = new JPopupMenu();
        JPanel checkboxPanel = createFilterCheckboxPanel();
        JScrollPane scrollfilter = new JScrollPane(checkboxPanel);
        scrollfilter.setPreferredSize(new Dimension(200, 100));
        popup.setLayout(new BorderLayout());
        popup.add(scrollfilter, BorderLayout.CENTER);
        return popup;
    }

    private JPanel createFilterCheckboxPanel() {
        String[] choices = {"Duration", "Location", "Sports", "Drawing", "Environmental"};
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        for (String item : choices) {
            checkboxPanel.add(new JCheckBox(item));
        }

        return checkboxPanel;
    }

    private JButton createFilterButton() {
        JButton filterButton = new JButton("Filters");
        filterButton.setPreferredSize(new Dimension(80, 30));
        return filterButton;
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
        SearchDataAccessInterface dataAccess = new EventDAO();
        SearchViewModel viewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchPresenter presenter = new SearchPresenter(viewModel, viewManagerModel);
        SearchInteractor interactor = new SearchInteractor(dataAccess, presenter);
        SearchController controller = new SearchController(interactor);

        SwingUtilities.invokeLater(() -> new Home_screen(viewModel, controller).setVisible(true));
    }
}
