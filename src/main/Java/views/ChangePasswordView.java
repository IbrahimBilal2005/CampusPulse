package views;


import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField currentPasswordField = new JTextField(15);
    private final JTextField newPasswordField = new JTextField(15);
    private final JTextField confirmPasswordField = new JTextField(15);
    private final JButton finishButton = new JButton("Finish");

    private ChangePasswordController changePasswordController;
    private final ChangePasswordViewModel viewModel;

    public ChangePasswordView(ChangePasswordViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Set up layout and components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Change Password", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panels for each input field
        JPanel usernamePanel = createFieldPanel("Username:", usernameInputField);
        JPanel currentPasswordPanel = createFieldPanel("Current Password:", currentPasswordField);
        JPanel newPasswordPanel = createFieldPanel("New Password:", newPasswordField);
        JPanel confirmPasswordPanel = createFieldPanel("Confirm Password:", confirmPasswordField);

        // Add components to the panel
        this.add(titleLabel);
        this.add(usernamePanel);
        this.add(currentPasswordPanel);
        this.add(newPasswordPanel);
        this.add(confirmPasswordPanel);
        this.add(finishButton);

        addListeners();  // Add listeners for the text fields and button
    }

    private JPanel createFieldPanel(String labelText, JTextField inputField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(labelText));
        panel.add(inputField);
        return panel;
    }

    private void addListeners() {
        // Add document listeners to input fields
        addTextFieldListener(usernameInputField, "username");
        addTextFieldListener(currentPasswordField, "currentPassword");
        addTextFieldListener(newPasswordField, "newPassword");
        addTextFieldListener(confirmPasswordField, "confirmPassword");

        // Explicit ActionListener for Finish button
        // Handle the Finish button click
        finishButton.addActionListener(this::handleFinishButtonClick);
    }

    private void addTextFieldListener(JTextField field, String fieldName) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState(fieldName, e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState(fieldName, e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState(fieldName, e);
            }

            private void updateState(String fieldName, DocumentEvent e) {
                String text = field.getText();  // Get the text from JTextField
                ChangePasswordState currentState = viewModel.getState();
                if (fieldName.equals("username")) {
                    currentState.setUsername(text);
                } else if (fieldName.equals("currentPassword")) {
                    currentState.setCurrentPassword(text);
                } else if (fieldName.equals("newPassword")) {
                    currentState.setNewPassword(text);
                } else if (fieldName.equals("confirmPassword")) {
                    currentState.setConfirmPassword(text);
                }
                viewModel.setState(currentState);  // Update the state in the ViewModel
            }
        });
    }

    private void handleFinishButtonClick(ActionEvent evt) {
        // Get the current state from the ViewModel
        ChangePasswordState currentState = viewModel.getState();

        // Pass the state to the controller to handle the password change
        changePasswordController.execute(
                currentState.getUsername(),
                currentState.getCurrentPassword(),
                currentState.getNewPassword(),
                currentState.getConfirmPassword()
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            // Update UI with current username from state
            usernameInputField.setText(state.getUsername());
        }
    }

    // Set the ChangePasswordController
    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }
}
