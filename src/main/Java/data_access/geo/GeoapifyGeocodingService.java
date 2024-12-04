package data_access.geo;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GeoapifyGeocodingService implements GeocodingService {

    private final String apiKey;

    public GeoapifyGeocodingService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Location getCoordinates(String query) {
        String url = "https://api.geoapify.com/v1/geocode/search?text=" + query + "&format=json&apiKey=" + apiKey;

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray results = jsonResponse.getJSONArray("results");

                if (results.length() > 0) {
                    JSONObject firstResult = results.getJSONObject(0);
                    double lat = firstResult.getDouble("lat");
                    double lon = firstResult.getDouble("lon");
                    return new Location(lat, lon);
                } else {
                    throw new RuntimeException("No results found for query: " + query);
                }
            } else {
                throw new IOException("Request failed with status code: " + response.code());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching coordinates: " + e.getMessage(), e);
        }
    }
}
