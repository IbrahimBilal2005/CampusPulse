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
    // controller for adding an event


    private JPanel eventsPanel;

    public MyEvents_screen(MyEventsViewModel myEventsViewModel) {
        this.myEventsViewModel = myEventsViewModel;
        this.myEventsViewModel.addPropertyChangeListener(this);
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
        eventsPanel.removeAll();
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

        deleteButton.addActionListener(e -> viewModel.deleteEvent(event));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        eventPanel.add(buttonPanel, BorderLayout.SOUTH);

        return eventPanel;
    }

    public static void main(String[] args) {
        // Example ViewModel instantiation
        MyEventsViewModel viewModel = new MyEventsViewModel();
        SwingUtilities.invokeLater(() -> new MyEvents_screen(viewModel).setVisible(true));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}