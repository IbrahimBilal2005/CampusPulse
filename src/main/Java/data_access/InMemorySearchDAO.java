package data_access;

import entity.Event;
import use_case.search.SearchDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemorySearchDAO implements SearchDataAccessInterface {
    private final List<Event> events;

    public InMemorySearchDAO(List<Event> events) {
        this.events = events;
    }

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

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
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
