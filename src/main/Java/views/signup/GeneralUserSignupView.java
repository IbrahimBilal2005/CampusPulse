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

    private final JTextField firstNameInputField = new JTextField(15);
    private final JTextField lastNameInputField = new JTextField(15);
    private final JTextField ageInputField = new JTextField(15);
    private final JPanel interestSelectionPanel = new JPanel();
    private final JScrollPane interestScrollPane = new JScrollPane(interestSelectionPanel);
    private final List<JCheckBox> interestCheckBoxes = new ArrayList<>();
    private final JComboBox<String> genderComboBox = new JComboBox<>();
    private final JPanel genderSelectionPanel = new JPanel();

    public GeneralUserSignupView(UserSignupViewModel userSignupViewModel) {
        super(userSignupViewModel);

        final JLabel title = new JLabel(UserSignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel firstNameInfo = new LabelTextPanel(new JLabel(UserSignupViewModel.FIRST_NAME_LABEL), firstNameInputField);
        final LabelTextPanel lastNameInfo = new LabelTextPanel(new JLabel(UserSignupViewModel.LAST_NAME_LABEL), lastNameInputField);
        final LabelTextPanel ageInfo = new LabelTextPanel(new JLabel(UserSignupViewModel.AGE_LABEL), ageInputField);

        setupInterestPicker();
        setupGenderComboBox();

        JPanel additionalFieldsPanel = new JPanel();
        additionalFieldsPanel.setLayout(new BoxLayout(additionalFieldsPanel, BoxLayout.Y_AXIS));

        additionalFieldsPanel.add(firstNameInfo);
        additionalFieldsPanel.add(lastNameInfo);
        additionalFieldsPanel.add(ageInfo);
        additionalFieldsPanel.add(genderSelectionPanel);
        additionalFieldsPanel.add(interestSelectionPanel);

        additionalFieldsContainer.add(additionalFieldsPanel);

        addFirstNameListener();
        addLastNameListener();
        addGenderListener();
        addAgeListener();
    }

    private void setupGenderComboBox() {
        genderSelectionPanel.add(new JLabel(UserSignupViewModel.GENDER_LABEL));
        genderSelectionPanel.add(genderComboBox);

        for (String genderOption : UserSignupViewModel.GENDER_SELECTION_OPTIONS) {
            genderComboBox.addItem(genderOption);
        }
        genderComboBox.setPreferredSize(new Dimension(200, 25));
        genderComboBox.addActionListener(e -> {
            UserSignupState currentState = viewModel.getState();
            currentState.setGender((String) genderComboBox.getSelectedItem());
            viewModel.setState(currentState);
        });
    }

    private void setupInterestPicker() {
        interestSelectionPanel.setLayout(new BoxLayout(interestSelectionPanel, BoxLayout.Y_AXIS));

        interestSelectionPanel.add(new JLabel(UserSignupViewModel.INTEREST_SELECTION_LABEL));
        UserSignupViewModel.INTEREST_SELECTION_OPTIONS.forEach(interest -> {
            JCheckBox checkBox = new JCheckBox(interest);
            interestCheckBoxes.add(checkBox);
            interestSelectionPanel.add(checkBox);
        });

        interestScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        interestScrollPane.setPreferredSize(new Dimension(200, 100));
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

        final UserSignupState currentState = viewModel.getState();
        currentState.setInterests(selectedInterests);
        viewModel.setState(currentState);
    }

    private void addFirstNameListener() {
        firstNameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                UserSignupState currentState = viewModel.getState();
                currentState.setFirstName(firstNameInputField.getText());
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

    private void addLastNameListener() {
        lastNameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                UserSignupState currentState = viewModel.getState();
                currentState.setLastName(lastNameInputField.getText());
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

    private void addGenderListener() {
        genderComboBox.addActionListener(e -> {
            String selectedGender = (String) genderComboBox.getSelectedItem();
            UserSignupState currentState = viewModel.getState();
            currentState.setGender(selectedGender);
            viewModel.setState(currentState);
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
        //changed since we are not using cancel button now
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
                        currentState.getFirstName(),
                        currentState.getLastName(),
                        currentState.getAge(),
                        currentState.getGender(),
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
