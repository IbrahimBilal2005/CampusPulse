package views;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import entity.Event;
import interface_adapter.delete_event.DeleteEventController;
import interface_adapter.delete_event.MyEventsViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

public class MyEvents_screen extends JFrame implements PropertyChangeListener {
    private MyEventsViewModel myEventsViewModel;
    private DeleteEventController deleteEventController;

    private LoggedInViewModel loggedInViewModel;

    private JPanel eventsPanel;
    private final JButton addEventButton;

    public MyEvents_screen(MyEventsViewModel myEventsViewModel, DeleteEventController deleteEventController) {
        this.myEventsViewModel = myEventsViewModel;
        this.myEventsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel addEventPanel = new JPanel();
        addEventButton = new JButton("Add Event");
        addEventPanel.add(addEventButton);

        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        eventsPanel.add(scrollPane);

        loadEvents();
    }

    private void loadEvents() {
        eventsPanel.removeAll();
        final LoggedInState loggedInState = new LoggedInState();
        List<Event> events = new ArrayList<>(loggedInState.getEvents().values());
        for (Event event : events) {
            eventsPanel.add(createEventPanel(event));
        }
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.add(new JLabel(event.getName()), BorderLayout.CENTER);

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

        eventPanel.add(detailsContentPanel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteEventController.deleteEvent(LoggedInState.getUsername(),  .getName()));

        JPanel buttonPanel = new JPanel();
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
