package App.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    public static void main(String[] args) {
        initializeFirebase();
    }

    public static void initializeFirebase() {
        try {
            // Replace the path below with the path to your downloaded service account key JSON file
            FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://campuspulse-1d252.firebaseio.com")  // Optional, needed for Realtime Database
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Firebase has been initialized successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
