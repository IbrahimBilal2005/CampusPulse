package views.signup;

import interface_adapter.event_poster_signup.EventPosterSignupController;
import interface_adapter.event_poster_signup.EventPosterSignupState;
import interface_adapter.event_poster_signup.EventPosterSignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventPosterSignupView extends BaseSignupView<EventPosterSignupViewModel> implements ActionListener, PropertyChangeListener {

    private EventPosterSignupController eventPosterSignupController;

    private final JTextField organizationNameInputField = new JTextField(15);
    private final JTextField sopLinkInputField = new JTextField(15);

    public EventPosterSignupView(EventPosterSignupViewModel eventPosterSignupViewModel) {
        super(eventPosterSignupViewModel);

        final LabelTextPanel organizationNameInfo = new LabelTextPanel(new JLabel(EventPosterSignupViewModel.ORGANIZATION_NAME_LABEL), organizationNameInputField);
        final LabelTextPanel sopLinkInfo = new LabelTextPanel(new JLabel(EventPosterSignupViewModel.SOP_LINK_LABEL), sopLinkInputField);

        JPanel additionalFieldsPanel = new JPanel();
        additionalFieldsPanel.setLayout(new BoxLayout(additionalFieldsPanel, BoxLayout.Y_AXIS));
        additionalFieldsPanel.add(organizationNameInfo);
        additionalFieldsPanel.add(sopLinkInfo);

        additionalFieldsContainer.add(additionalFieldsPanel);

        addOrganizationNameListener();
        addSopLinkListener();
    }

    private void addOrganizationNameListener() {
        organizationNameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final EventPosterSignupState currentState = viewModel.getState();
                currentState.setOrganizationName(organizationNameInputField.getText());
                viewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addSopLinkListener() {
        sopLinkInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final EventPosterSignupState currentState = viewModel.getState();
                currentState.setSopLink(sopLinkInputField.getText());
                viewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    protected void addSignupButtonListener() {
        signUp.addActionListener(evt -> {
                    if (evt.getSource().equals(signUp)) {
                        final EventPosterSignupState currentState = viewModel.getState();

                        eventPosterSignupController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getRepeatPassword(),
                                currentState.getOrganizationName(),
                                currentState.getSopLink()
                        );
                    }
                }
        );
    }

    @Override
    protected void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final EventPosterSignupState currentState = viewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                viewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

    }

    @Override
    protected void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final EventPosterSignupState currentState = viewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                viewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    protected void addConfirmPasswordListener() {
        confirmPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final EventPosterSignupState currentState = viewModel.getState();
                currentState.setRepeatPassword(new String(confirmPasswordInputField.getPassword()));
                viewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void addCancelButtonListener() {
        cancel.addActionListener(
                evt -> eventPosterSignupController.switchToBaseView()
        );
    }

    public String getViewName() {
        return "Event Poster Signup";
    }

    public void setAccountTypeController(EventPosterSignupController eventPosterSignupController) {
        this.eventPosterSignupController = eventPosterSignupController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final EventPosterSignupState state = (EventPosterSignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Choose Account Type Screen");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Add the view to the frame
            EventPosterSignupViewModel eventPosterSignupViewModel1 = new EventPosterSignupViewModel();
            frame.add(new EventPosterSignupView(eventPosterSignupViewModel1));
            frame.setVisible(true);
        });
    }
}
