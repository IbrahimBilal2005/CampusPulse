package data_access;

import data_access.geo.Location;
import org.junit.jupiter.api.*;
import data_access.geo.GeoapifyGeocodingService;

import static org.junit.jupiter.api.Assertions.*;

class GeoapifyGeocodingServiceTest {

    private GeoapifyGeocodingService geocodingService;

    @BeforeEach
    void setUp() {
        String apiKey = "00b11d0dc6c34c75bb7f719c3d745872"; // Use a mock or dummy key for testing
        geocodingService = new GeoapifyGeocodingService(apiKey);
    }

    @Test
    void testGetCoordinatesSuccess() {
        String query = "sidney smith, Toronto";
        Location location = geocodingService.getCoordinates(query);

        assertNotNull(location);
        assertEquals(43.660493, location.getLatitude(), 0.02);
        assertEquals(-79.395125, location.getLongitude(), 0.02);
    }

    @Test
    void testGetCoordinatesNoResults() {
        String query = "nonexistent, place";

        // Execute the method and catch the exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Location location = geocodingService.getCoordinates(query);
            System.out.println("Returned location: " + location); // Log the result if no exception is thrown
        });

        // Log the exception message for debugging
        System.out.println("Exception message: " + exception.getMessage());

        // Assert that the exception message contains the expected string
        assertTrue(exception.getMessage().contains("No results found"));
    }

    @Test
    void testGetCoordinatesInvalidApiKey() {
        geocodingService = new GeoapifyGeocodingService("INVALID_API_KEY");
        String query = "sidney smith, Toronto";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            geocodingService.getCoordinates(query);
        });

        assertTrue(exception.getMessage().contains("Request failed with status code"));
    }
}
