package views;

import interface_adapter.filter.FilterController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_screen extends JPanel implements ActionListener{

    private LoginController controller;

    public Login_screen(LoginViewModel view) {

        // Create a new frame
        JFrame frame = new JFrame(view.LOGIN_SCREEN); // Create Frame and have it close when the x is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set the screen size to a quarter the users screen and center it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);

        //Made a banner with application name at the top
        JLabel bannerLabel = new JLabel(view.TITLE);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        bannerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around the text
        bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label in BoxLayout

        //Add a line to show that the above is a banner of sorts
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // Make the separator span the full width
        separator.setForeground(Color.BLACK);

        //Create panel (container) for the username field
        JPanel username_panel = new JPanel();
        username_panel.add(new JLabel(view.USERNAME));
        JTextField username_input = new JTextField(20);
        username_panel.add(username_input);
        
        //Create panel (container) for the password field
        JPanel password_panel = new JPanel();
        password_panel.add(new JLabel(view.PASSWORD));
        JPasswordField password_input = new JPasswordField(20);
        password_panel.add(password_input);

        //Added in login button
        JPanel login_panel = new JPanel();
        JButton login = new JButton(view.LOG_IN);
        login_panel.add(login);

        //Create the main panel and set the frame to display it the order matters since some things should not be centered within the frame
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.add(bannerLabel);
        main_panel.add(separator);
        main_panel.add(Box.createVerticalGlue());
        main_panel.add(username_panel);
        main_panel.add(password_panel);
        main_panel.add(login_panel);
        frame.setContentPane(main_panel);
        frame.setVisible(true);

        // Action listener for login button that validates the username and password and then send them to the home screen
        login.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            System.out.println("click");
                            final LoginState currentState = view.getState();
                            controller.execute(currentState.getUsername(), currentState.getPassword()
                            );
                        }
                    }
                }
        );

        // update itself everytime an action is done within the username field
        username_input.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = view.getState();
                currentState.setUsername(username_input.getText());
                view.setState(currentState);
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

        // update itself everytime an action is done within the password field
        password_input.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = view.getState();
                currentState.setPassword(new String(password_input.getPassword()));
                view.setState(currentState);
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
    public void actionPerformed(ActionEvent e) {

    }

    public String getViewName() {
        return "Event Poster Signup";
    }

    public void setLoginController(LoginController loginControllerController) {
        this.controller = loginControllerController;
    }
}

