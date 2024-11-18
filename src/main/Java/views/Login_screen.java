package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_screen {

    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Login Screen"); // Create Frame and have it close when the x is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set the screen size to a quarter the users screen and center it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);

        //Made a banner with application name at the top
        JLabel bannerLabel = new JLabel("CampusPulse");
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        bannerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around the text
        bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label in BoxLayout

        //Add a line to show that the above is a banner of sorts
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // Make the separator span the full width
        separator.setForeground(Color.BLACK);

        //Create panel (container) for the username field
        JPanel username_panel = new JPanel();
        username_panel.add(new JLabel("Username:"));
        username_panel.add(new JTextField(20));
        //Create panel (container) for the password field
        JPanel password_panel = new JPanel();
        password_panel.add(new JLabel("Password:"));
        password_panel.add(new JTextField(20));
        //Added in login button
        JPanel button_panel = new JPanel();
        button_panel.add(new JButton("Login"));

        //Create the main panel and set the frame to display it the order matters since some things should not be centered within the frame
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.add(bannerLabel);
        main_panel.add(separator);
        main_panel.add(Box.createVerticalGlue());
        main_panel.add(username_panel);
        main_panel.add(password_panel);
        main_panel.add(button_panel);
        frame.setContentPane(main_panel);
        frame.setVisible(true);

        //ActionListeners to be added
    }
}

