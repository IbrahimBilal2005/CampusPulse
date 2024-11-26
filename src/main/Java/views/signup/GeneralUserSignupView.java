package views.signup;

import interface_adapter.signup.general_user_signup.UserSignupController;
import interface_adapter.signup.general_user_signup.UserSignupState;
import interface_adapter.signup.general_user_signup.UserSignupViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class GeneralUserSignupView extends BaseSignupView<UserSignupViewModel> implements ActionListener, PropertyChangeListener {

    private UserSignupController userSignupController;

    private final JTextField genderInputField = new JTextField(15);
    private final JTextField ageInputField = new JTextField(15);
    private final JPanel interestSelectionPanel = new JPanel();
    private final JScrollPane interestScrollPane = new JScrollPane(interestSelectionPanel);
    private final List<JCheckBox> interestCheckBoxes = new ArrayList<>();

    public GeneralUserSignupView(UserSignupViewModel userSignupViewModel) {
        super(userSignupViewModel);

        final JLabel title = new JLabel(UserSignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel genderInfo = new LabelTextPanel(new JLabel(UserSignupViewModel.GENDER_LABEL), genderInputField);
        final LabelTextPanel ageInfo = new LabelTextPanel(new JLabel(UserSignupViewModel.AGE_LABEL), ageInputField);

        setupInterestPicker();

        JPanel additionalFieldsPanel = new JPanel();
        additionalFieldsPanel.setLayout(new BoxLayout(additionalFieldsPanel, BoxLayout.Y_AXIS));
        additionalFieldsPanel.add(genderInfo);
        additionalFieldsPanel.add(ageInfo);
        additionalFieldsPanel.add(interestScrollPane);

        additionalFieldsContainer.add(additionalFieldsPanel);

        addGenderListener();
        addAgeListener();
    }

    private void setupInterestPicker() {
        interestSelectionPanel.setLayout(new BoxLayout(interestSelectionPanel, BoxLayout.Y_AXIS));

        JLabel interestLabel = new JLabel("Select Your Interests:");
        interestLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        interestSelectionPanel.add(interestLabel);

        UserSignupViewModel.INTEREST_SELECTION_OPTIONS.forEach(interest -> {
            JCheckBox checkBox = new JCheckBox(interest);
            interestCheckBoxes.add(checkBox);
            interestSelectionPanel.add(checkBox);
        });

        interestScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        interestScrollPane.setPreferredSize(new Dimension(200, 100)); // Adjust size as needed

        addInterestListeners();
    }

    private void addInterestListeners() {
        for (JCheckBox checkBox : interestCheckBoxes) {
            checkBox.addActionListener(e -> updateSelectedInterests());
        }
    }

    private void updateSelectedInterests() {
        List<String> selectedInterests = new ArrayList<>();
        for (JCheckBox checkBox : interestCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedInterests.add(checkBox.getText());
            }
        }

        // Update the view model state with the selected interests
        final UserSignupState currentState = viewModel.getState();
        currentState.setInterests(selectedInterests); // Assuming there's a setSelectedInterests method
        viewModel.setState(currentState);
    }


    private void addGenderListener() {
        genderInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                UserSignupState currentState = viewModel.getState();
                currentState.setGender(genderInputField.getText());
                viewModel.setState(currentState);
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

    private void addAgeListener() {
        ageInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                UserSignupState currentState = viewModel.getState();
                try {
                    currentState.setAge(Integer.parseInt(ageInputField.getText().trim()));
                    currentState.setAgeError(null);
                } catch (NumberFormatException e) {
                    currentState.setAgeError("Please enter a valid age.");
                }
                viewModel.setState(currentState);
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
    protected void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final UserSignupState currentState = viewModel.getState();
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
                final UserSignupState currentState = viewModel.getState();
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
                final UserSignupState currentState = viewModel.getState();
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
                evt -> userSignupController.switchToBaseView()
        );
    }

    @Override
    protected void addSignupButtonListener() {
        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                final UserSignupState currentState = viewModel.getState();
                userSignupController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.getRepeatPassword(),
                        currentState.getGender(),
                        currentState.getAge(),
                        currentState.getInterests());
            }
        });
    }

    public void setAccountTypeController(UserSignupController userSignupController) {
        this.userSignupController = userSignupController;
    }

    public String getViewName() {
        return "General User Signup";
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserSignupState state = (UserSignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("General User Signup Screen");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Add the view to the frame
            UserSignupViewModel userSignupViewModel = new UserSignupViewModel();
            frame.add(new GeneralUserSignupView(userSignupViewModel));
            frame.setVisible(true);
        });
    }
}
