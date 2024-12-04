package data_access.geo;

public interface GeocodingService {
    Location getCoordinates(String query);
}
