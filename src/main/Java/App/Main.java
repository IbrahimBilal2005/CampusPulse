package App;

import App.config.FirebaseInitializer;
import App.auth.AuthService;

public class Main {
    public static void main(String[] args) {
        // Initialize Firebase
        FirebaseInitializer.initializeFirebase();

        // Create a new user example
        AuthService.createUser("jerry@utoronto.ca", "securepassword");
    }
}
