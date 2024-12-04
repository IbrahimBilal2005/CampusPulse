package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel {
    private JButton loginButton;
    private JButton signupButton;

    public WelcomeView(ActionListener loginAction, ActionListener signupAction) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create and configure login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(loginAction);

        // Create and configure signup button
        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(signupAction);

        // Add buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(signupButton, gbc);
    }

    public String getViewName() {
        return "Welcome"; // Unique name for the WelcomeScreen
    }
}