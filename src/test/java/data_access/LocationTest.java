package data_access;

import data_access.geo.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testLocationConstructorAndGetters() {
        // Arrange
        double latitude = 40.7128;
        double longitude = -74.0060; // New York coordinates

        // Act
        Location location = new Location(latitude, longitude);

        // Assert
        assertEquals(latitude, location.getLatitude(), "Latitude should match the provided value");
        assertEquals(longitude, location.getLongitude(), "Longitude should match the provided value");
    }

    @Test
    void testToStringMethod() {
        // Arrange
        double latitude = 51.5074;
        double longitude = -0.1278; // London coordinates
        Location location = new Location(latitude, longitude);

        // Act
        String expectedString = "Location{latitude=51.5074, longitude=-0.1278}";
        String actualString = location.toString();

        // Assert
        assertEquals(expectedString, actualString, "toString method should return the expected string format");
    }
}
