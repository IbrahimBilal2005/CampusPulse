package views;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import entity.Event;
import interface_adapter.delete_event.DeleteEventController;
import interface_adapter.delete_event.MyEventsViewModel;

public class MyEvents_screen extends JFrame implements PropertyChangeListener {
    private MyEventsViewModel myEventsViewModel;
    private DeleteEventController deleteEventController;

    private JPanel eventsPanel;

    public MyEvents_screen(MyEventsViewModel myEventsViewModel, DeleteEventController deleteEventController) {
        this.myEventsViewModel = myEventsViewModel;
        this.deleteEventController = deleteEventController;
        this.myEventsViewModel.addPropertyChangeListener(this); // Listener for changes in events
        setupFrame();
        initializeComponents();
        loadEvents();
    }

    private void setupFrame() {
        setTitle("My Events");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        add(scrollPane);
    }

    private void loadEvents() {
        // Remove any old event panels
        eventsPanel.removeAll();
        // Get the list of events from the ViewModel
        List<Event> events = myEventsViewModel.getEvents();
        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.add(new JLabel(event.getName()), BorderLayout.CENTER);
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // When delete button is clicked, delete the event using the controller
        deleteButton.addActionListener(e -> deleteEventController.deleteEvent(event.getName()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        eventPanel.add(buttonPanel, BorderLayout.SOUTH);

        return eventPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("events".equals(evt.getPropertyName())) {
            // Whenever the events change in the ViewModel, reload them in the view
            loadEvents();
        }
    }

    public static void main(String[] args) {
        // Example ViewModel instantiation
        MyEventsViewModel viewModel = new MyEventsViewModel();
        // Example controller instantiation (you will likely pass a real controller here)
        DeleteEventController controller = new DeleteEventController(viewModel);
        SwingUtilities.invokeLater(() -> new MyEvents_screen(viewModel, controller).setVisible(true));
    }
}
