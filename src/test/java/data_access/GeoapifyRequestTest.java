package data_access;

import data_access.geo.GeoapifyGeocodingService;
import data_access.geo.GeoapifyRequest;
import data_access.geo.Location;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class GeoapifyRequestTest {

    // Mock GeoapifyGeocodingService for unit testing purposes
    class MockGeoapifyGeocodingService extends GeoapifyGeocodingService {
        public MockGeoapifyGeocodingService(String apiKey) {
            super(apiKey);
        }

        @Override
        public Location getCoordinates(String query) {
            // Return a fixed location for testing
            return new Location(51.5074, -0.1278); // London coordinates
        }
    }

    @Test
    void testGetMapImage() throws Exception {
        // Using the mock Geoapify service
        GeoapifyRequest geoRequest = new GeoapifyRequest("mockApiKey");
        geoRequest = new GeoapifyRequest("00b11d0dc6c34c75bb7f719c3d745872") {
            protected GeoapifyGeocodingService createGeocodingService(String apiKey) {
                return new MockGeoapifyGeocodingService(apiKey);
            }
        };

        // Call the method with a location name
        BufferedImage mapImage = geoRequest.getMapImage("London");

        // Assert that we got a non-null image
        assertNotNull(mapImage, "Map image should not be null");
    }
}
