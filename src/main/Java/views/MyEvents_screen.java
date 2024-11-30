package views;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import entity.Event;
import interface_adapter.MyEventsViewModel;

public class MyEventsScreen extends JFrame {
    private MyEventsViewModel viewModel;
    private JPanel eventsPanel;

    public MyEventsScreen(MyEventsViewModel viewModel) {
        this.viewModel = viewModel;
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
        List<Event> events = viewModel.getEvents();
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

        editButton.addActionListener(e -> viewModel.editEvent(event));
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
        SwingUtilities.invokeLater(() -> new MyEventsScreen(viewModel).setVisible(true));
    }
}