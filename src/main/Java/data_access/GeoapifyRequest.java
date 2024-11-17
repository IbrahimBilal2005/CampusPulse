package data_access;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GeoapifyRequest {

    public static void main(String[] args) {
        // Set up the frame to display the image
        JFrame frame = new JFrame("Geoapify Static Map Example");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // URL for Geoapify Static Map API
        String apiKey = "00b11d0dc6c34c75bb7f719c3d745872";  // Replace with your Geoapify API key
        String urlString = "https://maps.geoapify.com/v1/staticmap?style=osm-bright&width=600&height=400&center=lonlat:-122.304378,47.526022&zoom=14&scaleFactor=2&apiKey=" + apiKey;

        // Fetch the image from the URL
        try {
            // Open a connection to the Geoapify API
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the input stream and read the image
            InputStream inputStream = connection.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);

            // Display the image in a JLabel
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Refresh the frame to show the image
            frame.revalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
