package data_access;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GeoapifyRequest {

    private GeoapifyGeocodingService geocodingService;

    public GeoapifyRequest(String apiKey) {
        this.geocodingService = new GeoapifyGeocodingService(apiKey);
    }

    // Method to fetch the map image based on location name
    public BufferedImage getMapImage(String locationName) throws Exception {
        // Get coordinates from GeoapifyGeocodingService using the location name
        Location location = geocodingService.getCoordinates(locationName);

        // Convert the coordinates into a string suitable for the Geoapify API
        String coordinates = location.getLongitude() + "," + location.getLatitude(); // longitude,latitude

        // Build the Geoapify static map URL with the coordinates
        String apiKey = "00b11d0dc6c34c75bb7f719c3d745872";  // Replace with your Geoapify API key
        String urlString = "https://maps.geoapify.com/v1/staticmap?style=osm-bright&width=600&height=400&center=lonlat:"
                + coordinates + "&zoom=14&scaleFactor=2&apiKey=" + apiKey;

        // Open a connection to the Geoapify API
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get the input stream and read the image
        InputStream inputStream = connection.getInputStream();
        return ImageIO.read(inputStream);
    }
}
