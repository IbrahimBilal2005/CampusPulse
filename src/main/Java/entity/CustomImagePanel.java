package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomImagePanel extends JPanel {
    private BufferedImage map;

    // Method to set the image
    public void setMap(BufferedImage map) {
        this.map = map;
        repaint(); // Repaint the panel to display the new image
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to clear the panel
        if (map != null) {
            g.drawImage(map, 0, 0, getWidth(), getHeight(), this); // Draw the image
        }
    }

}
