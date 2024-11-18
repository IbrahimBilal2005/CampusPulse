package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_screen {

    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocationRelativeTo(null);


        JPanel login_panel = new JPanel();
        login_panel.add(new JLabel("Username:"));
        login_panel.add(new JTextField(20));

        JPanel password_panel = new JPanel();
        password_panel.add(new JLabel("Password:"));
        password_panel.add(new JTextField(20));

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
        button_panel.add(new JButton("Login"));


        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        main_panel.add(Box.createVerticalGlue());
        main_panel.add(login_panel);
        main_panel.add(password_panel);
        main_panel.add(button_panel);
        frame.setContentPane(main_panel);
        frame.setVisible(true);
    }
}

