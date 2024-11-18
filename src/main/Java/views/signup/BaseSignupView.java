package views.signup;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public abstract class BaseSignupView<T extends ViewModel<?>> extends JPanel implements ActionListener, PropertyChangeListener {

    protected T viewModel;

    protected JTextField usernameInputField = new JTextField(15);
    protected JPasswordField passwordInputField = new JPasswordField(15);
    protected JPasswordField confirmPasswordInputField = new JPasswordField(15);

    protected final JButton cancel;
    protected final JButton signUp;
    protected final JButton next;
    protected final JButton back;

    protected final JPanel initialFieldsPanel;
    protected final JPanel additionalFieldsPanel;
    protected final JPanel initialButtonsPanel;

    protected final JPanel additionalFieldsContainer; // NEW FIELD
    protected final JPanel additionalButtonsPanel;

    public BaseSignupView(T viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Initialize buttons
        cancel = new JButton("Cancel");
        signUp = new JButton("Sign Up");
        next = new JButton("Next");
        back = new JButton("Back");

        // Set button panels
        initialButtonsPanel = new JPanel();
        initialButtonsPanel.add(next);

        additionalButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Modified layout
        additionalButtonsPanel.add(back);
        additionalButtonsPanel.add(signUp);

        // Set up the input fields
        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(new JLabel("Confirm Password"), confirmPasswordInputField);

        // Panels for the input fields
        initialFieldsPanel = new JPanel();
        initialFieldsPanel.setLayout(new BoxLayout(initialFieldsPanel, BoxLayout.Y_AXIS));
        initialFieldsPanel.add(usernameInfo);
        initialFieldsPanel.add(passwordInfo);
        initialFieldsPanel.add(repeatPasswordInfo);
        initialFieldsPanel.add(initialButtonsPanel);

        additionalFieldsContainer = new JPanel(); // NEW FIELD
        additionalFieldsContainer.setLayout(new BoxLayout(additionalFieldsContainer, BoxLayout.Y_AXIS)); // Flexible for child classes

        additionalFieldsPanel = new JPanel(new BorderLayout()); // Modified layout
        additionalFieldsPanel.add(additionalFieldsContainer, BorderLayout.CENTER); // Child classes add here
        additionalFieldsPanel.add(additionalButtonsPanel, BorderLayout.SOUTH);
        additionalFieldsPanel.setVisible(false);

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cancelPanel.add(cancel);

        // Add listeners
        addBackButtonListener();
        addNextButtonListener();
        addSignupButtonListener();
        addCancelButtonListener();

        addUsernameListener();
        addConfirmPasswordListener();
        addPasswordListener();

        // Add the components to the panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Signup Screen"));
        this.add(initialFieldsPanel);
        this.add(additionalFieldsPanel);
        this.add(cancelPanel);
    }

    protected void showAdditionalFields() {
        initialFieldsPanel.setVisible(false);
        initialButtonsPanel.setVisible(false);
        additionalFieldsPanel.setVisible(true);
        additionalButtonsPanel.setVisible(true);
        revalidate();
        repaint();
    }

    protected void showInitialFields() {
        additionalFieldsPanel.setVisible(false);
        additionalButtonsPanel.setVisible(false);
        initialFieldsPanel.setVisible(true);
        initialButtonsPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void addBackButtonListener() {
        back.addActionListener(e -> showInitialFields());
    }

    private void addNextButtonListener() {
        next.addActionListener(evt -> {
            if (evt.getSource().equals(next)) {
                showAdditionalFields();
            }
        });
    }

    protected abstract void addSignupButtonListener();
    protected abstract void addUsernameListener();
    protected abstract void addPasswordListener();
    protected abstract void addConfirmPasswordListener();
    public abstract void addCancelButtonListener();

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }
}
