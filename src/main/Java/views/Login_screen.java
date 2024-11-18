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

       
    }
}

