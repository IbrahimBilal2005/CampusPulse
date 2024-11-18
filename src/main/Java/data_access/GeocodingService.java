package data_access;

public interface GeocodingService {
    Location getCoordinates(String query);
}
