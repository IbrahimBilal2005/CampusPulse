package views;


import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.new_event_post.NewEventPostContoller;
import interface_adapter.new_event_post.NewEventPostInState;
import interface_adapter.new_event_post.NewEventViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewEventPostView extends JPanel implements PropertyChangeListener {

    private final JTextField eventNameField = new JTextField(15);
    private final JTextArea descriptionArea = new JTextArea(3, 15);
    private final JTextField locationField = new JTextField(15);
    private final JTextField startField = new JTextField(15);
    private final JTextField endField = new JTextField(15);
    private final JComboBox<String> tag1ComboBox = new JComboBox<>(new String[]{"Sports", "Music", "Technology", "Education", "Art", "Environment"});
    private final JComboBox<String> tag2ComboBox = new JComboBox<>(new String[]{"Education", "Music", "Technology", "Sports", "Art", "Environment"});
    private final JButton postButton = new JButton("Post Event");
    private final JLabel feedbackLabel = new JLabel("");

    private final NewEventPostContoller controller;
    private final NewEventViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;

    public NewEventPostView(NewEventViewModel viewModel, NewEventPostContoller controller, LoggedInViewModel loggedInViewModel) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.loggedInViewModel = loggedInViewModel;

        this.viewModel.addPropertyChangeListener(this);
        this.loggedInViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Create New Event");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Input Fields
        add(createFieldPanel("Event Name:", eventNameField));
        add(createFieldPanel("Description:", new JScrollPane(descriptionArea)));
        add(createFieldPanel("Location:", locationField));
        add(createFieldPanel("Start (dd-MM-yyyy HH:mm 24 hrs):", startField));
        add(createFieldPanel("End (dd-MM-yyyy HH:mm 24 hrs):", endField));
        add(createFieldPanel("Tag 1:", tag1ComboBox));
        add(createFieldPanel("Tag 2:", tag2ComboBox));

        // Feedback Label
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackLabel.setForeground(Color.RED);
        add(feedbackLabel);

        // Post Button
        postButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        postButton.addActionListener(e -> executeUseCase());
        add(postButton);

        // Add listeners for text fields
        addInputListeners();
    }

    private void executeUseCase() {
        // Retrieve the current state from the ViewModel
        NewEventPostInState currentState = viewModel.getState();
        final LoggedInState currentLoggedInState = loggedInViewModel.getState();
        feedbackLabel.setText("Event successfully created!");
        feedbackLabel.setForeground(Color.GREEN);
        // Pass the input data from the view's state to the controller
        controller.execute(
                currentState.getEventName(),
                currentState.getDescription(),
                currentState.getLocation(),
                currentState.getStart(),
                currentState.getEnd(),
                currentState.getTag1(),
                currentState.getTag2(),
                currentLoggedInState.getUsername()
        );
    }


    private JPanel createFieldPanel(String label, Component input) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(label));
        panel.add(input);
        return panel;
    }

    private void addInputListeners() {
        addTextFieldListener(eventNameField, "eventName");
        addTextFieldListener(locationField, "location");
        addTextFieldListener(startField, "start");
        addTextFieldListener(endField, "end");

        descriptionArea.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                NewEventPostInState state = viewModel.getState();
                state.setDescription(descriptionArea.getText());
                viewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }
        });

        tag1ComboBox.addActionListener(e -> {
            NewEventPostInState state = viewModel.getState();
            state.setTag1((String) tag1ComboBox.getSelectedItem());
            viewModel.setState(state);
        });

        tag2ComboBox.addActionListener(e -> {
            NewEventPostInState state = viewModel.getState();
            state.setTag2((String) tag2ComboBox.getSelectedItem());
            viewModel.setState(state);
        });
    }

    private void addTextFieldListener(JTextField field, String fieldName) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                NewEventPostInState state = viewModel.getState();
                switch (fieldName) {
                    case "eventName":
                        state.setEventName(field.getText());
                        break;
                    case "location":
                        state.setLocation(field.getText());
                        break;
                    case "start":
                        state.setStart(field.getText());
                        break;
                    case "end":
                        state.setEnd(field.getText());
                        break;

                }
                viewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            NewEventPostInState state = (NewEventPostInState) evt.getNewValue();

            // Handle errors and feedback
            if (state.getEventNameError() != null) {
                feedbackLabel.setText(state.getEventNameError());
            } else if (state.getLocationError() != null) {
                feedbackLabel.setText(state.getLocationError());
            } else if (state.getStartError() != null) {
                feedbackLabel.setText(state.getStartError());
            } else if (state.getEndError() != null) {
                feedbackLabel.setText(state.getEndError());
            } else {
//                feedbackLabel.setText("Event successfully created!");
//                feedbackLabel.setForeground(Color.GREEN);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("New Event Post Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            // Create a simple ViewModel for testing
            NewEventViewModel viewModel = new NewEventViewModel();
            NewEventPostContoller controller = null; // No controller functionality needed for now
            LoggedInViewModel loggedInViewModel = new LoggedInViewModel();

            NewEventPostView view = new NewEventPostView(viewModel, controller, loggedInViewModel);

            frame.add(view);
            frame.setVisible(true);
        });
    }
}

