package data_access;

/*import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;*/
import entity.Event;
//import org.bson.Document;
import use_case.filter.FilterDataAccessInterface;
import use_case.search.SearchDataAccessInterface;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//import static com.mongodb.client.model.Filters.eq;

public class EventDAO implements SearchDataAccessInterface, FilterDataAccessInterface {
    //private final MongoCollection<Document> eventCollection;
    private final List<Event> events = new ArrayList<>();

    // Constructor initializes the DAO and loads events from the database
    /*public EventDAO(MongoCollection<Document> eventCollection) {
        this.eventCollection = eventCollection;

        // Load events from the database into memory
        try (MongoCursor<Document> cursor = eventCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                events.add(documentToEvent(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public EventDAO() {
            // Example events with randomized data
            events.add(new Event(
                    "Tech Talk: AI in Healthcare",
                    "A discussion on how AI is revolutionizing healthcare.",
                    "Room 101, Main Building",
                    LocalDateTime.of(2024, 12, 5, 14, 0),
                    LocalDateTime.of(2024, 12, 5, 16, 0),
                    Arrays.asList("AI", "Healthcare", "Technology")
            ));

            events.add(new Event(
                    "Football Tournament Finals",
                    "The final match of the university football tournament.",
                    "Sports Complex, Field 3",
                    LocalDateTime.of(2024, 12, 6, 18, 0),
                    LocalDateTime.of(2024, 12, 6, 20, 0),
                    Arrays.asList("Sports", "Football", "Tournament")
            ));

            events.add(new Event(
                    "Startup Pitch Night",
                    "Join us for a night of exciting startup pitches.",
                    "Room 205, Innovation Hub",
                    LocalDateTime.of(2024, 12, 7, 19, 0),
                    LocalDateTime.of(2024, 12, 7, 21, 0),
                    Arrays.asList("Business", "Startup", "Networking")
            ));

            events.add(new Event(
                    "Yoga for Beginners",
                    "A relaxing session of yoga for all levels.",
                    "Wellness Center, Yoga Studio",
                    LocalDateTime.of(2024, 12, 8, 10, 0),
                    LocalDateTime.of(2024, 12, 8, 11, 30),
                    Arrays.asList("Fitness", "Yoga", "Wellness")
            ));

            events.add(new Event(
                    "AI for Beginners Workshop",
                    "A hands-on workshop introducing AI concepts.",
                    "Room 301, Computer Science Building",
                    LocalDateTime.of(2024, 12, 10, 15, 0),
                    LocalDateTime.of(2024, 12, 10, 17, 0),
                    Arrays.asList("AI", "Workshop", "Technology")
            ));

            events.add(new Event(
                    "Music Jam Session",
                    "An open music jam session for all musicians.",
                    "Music Room, Arts Building",
                    LocalDateTime.of(2024, 12, 12, 17, 0),
                    LocalDateTime.of(2024, 12, 12, 19, 0),
                    Arrays.asList("Music", "Jam Session", "Art")
            ));

        events.add(new Event(
                "Mat137",
                "Math Lecture.",
                "Toronto",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                Arrays.asList("Math", "Jam Session", "tech")
        ));
    }

    // Converts a MongoDB Document into an Event object
    /*private Event documentToEvent(Document doc) {
        return new Event(
                doc.getString("id"),
                doc.getString("name"),
                doc.getString("description"),
                doc.getString("location"),
                doc.getDate("date"),
                doc.getList("categories", String.class)
        );
    }

    // Converts an Event object into a MongoDB Document
    private Document eventToDocument(Event event) {
        return new Document()
                .append("id", event.getId())
                .append("name", event.getName())
                .append("description", event.getDescription())
                .append("location", event.getLocation())
                .append("date", event.getDate())
                .append("categories", event.getCategories());
    }*/

    // Get all events
    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    // Save an event to the database and in-memory list
    /*public void saveEvent(Event event) {
        Document doc = eventToDocument(event);
        eventCollection.insertOne(doc);
        events.add(event);
    }

    // Update an event in the database and in-memory list
    public void updateEvent(String eventId, Event updatedEvent) {
        Document filter = new Document("id", eventId);
        Document update = new Document("$set", eventToDocument(updatedEvent));

        eventCollection.updateOne(filter, update);

        // Update in-memory list
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(eventId)) {
                events.set(i, updatedEvent);
                break;
            }
        }
    }

    // Delete an event from the database and in-memory list
    public void deleteEvent(String eventId) {
        Document filter = new Document("id", eventId);
        eventCollection.deleteOne(filter);

        // Remove from in-memory list
        events.removeIf(event -> event.getId().equals(eventId));
    }*/

    // Method to filter events based on criteria
    @Override
    public List<Event> filterEvents(Map<String, Object> filterCriteria) {
        // Safely retrieve the duration and handle null values
        Integer duration = (Integer) filterCriteria.getOrDefault("duration", 0);

        // Retrieve other filters
        String location = (String) filterCriteria.get("location");
        List<String> tags = (List<String>) filterCriteria.getOrDefault("tags", Collections.emptyList());

        //revieve query
        String query = (String) filterCriteria.get("query");

        List<Event> filterevents = searchEvents(query);

        // If no filters are selected, return all events
        if (duration == 0 && location == null && tags.isEmpty()) {
            return filterevents; // Assuming `events` contains all stored events
        }

        // Apply filters
        return filterevents.stream()
                .filter(event -> {
                    // Filter by duration if specified
                    boolean matchesDuration = true;
                    if (duration != 0) {
                        // Calculate the event's duration (in hours)
                        long eventDuration = Duration.between(event.getStart(), event.getEnd()).toHours();
                        matchesDuration = eventDuration == duration;
                    }

                    // Filter by location if specified
                    boolean matchesLocation = location == null || event.getLocation().toLowerCase().contains(location.toLowerCase());

                    // Filter by tags if specified
                    boolean matchesTags = tags.isEmpty() || event.getTags().stream().anyMatch(tags::contains);

                    // Return true if all filters match
                    return matchesDuration && matchesLocation && matchesTags;
                })
                .collect(Collectors.toList());
    }




    // Search events based on the query using a basic string matching algorithm
    @Override
    public List<Event> searchEvents(String query) {
        String lowerCaseQuery = query.toLowerCase();

        return events.stream()
                .filter(event ->
                        // Match event name using Levenshtein Distance or Cosine Similarity
                        Levenshtein.isSimilar(event.getName(), lowerCaseQuery, 3) ||
                                CosineSimilarity.isSimilar(event.getName(), lowerCaseQuery, 0.7) ||
                                // Match event tags using Levenshtein or Cosine Similarity
                                event.getTags().stream().anyMatch(tag ->
                                        Levenshtein.isSimilar(tag, lowerCaseQuery, 3) ||
                                                CosineSimilarity.isSimilar(tag, lowerCaseQuery, 0.7)
                                )
                )
                .collect(Collectors.toList());
    }

    // Levenshtein Distance Utility
    public static class Levenshtein {
        public static int calculate(String a, String b) {
            int[][] dp = new int[a.length() + 1][b.length() + 1];

            for (int i = 0; i <= a.length(); i++) {
                for (int j = 0; j <= b.length(); j++) {
                    if (i == 0) {
                        dp[i][j] = j; // Base case: insert all characters
                    } else if (j == 0) {
                        dp[i][j] = i; // Base case: delete all characters
                    } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1]; // Characters match
                    } else {
                        dp[i][j] = 1 + Math.min(
                                dp[i - 1][j],      // Delete
                                Math.min(
                                        dp[i][j - 1],  // Insert
                                        dp[i - 1][j - 1] // Replace
                                )
                        );
                    }
                }
            }
            return dp[a.length()][b.length()];
        }

        public static boolean isSimilar(String a, String b, int threshold) {
            return calculate(a.toLowerCase(), b.toLowerCase()) <= threshold;
        }
    }

    // Cosine Similarity Utility
    public static class CosineSimilarity {
        public static double calculate(String a, String b) {
            Map<String, Integer> frequencyA = wordFrequency(a.toLowerCase());
            Map<String, Integer> frequencyB = wordFrequency(b.toLowerCase());

            double dotProduct = 0.0;
            double magnitudeA = 0.0;
            double magnitudeB = 0.0;

            for (String key : frequencyA.keySet()) {
                int freqA = frequencyA.get(key);
                int freqB = frequencyB.getOrDefault(key, 0);

                dotProduct += freqA * freqB;
                magnitudeA += freqA * freqA;
            }

            for (int freq : frequencyB.values()) {
                magnitudeB += freq * freq;
            }

            return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
        }

        private static Map<String, Integer> wordFrequency(String text) {
            Map<String, Integer> frequency = new HashMap<>();
            String[] words = text.split("\\s+");

            for (String word : words) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
            return frequency;
        }

        public static boolean isSimilar(String a, String b, double threshold) {
            return calculate(a, b) >= threshold;
        }
    }
}
