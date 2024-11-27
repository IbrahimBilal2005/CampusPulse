package data_access.readDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for reading database connection details from files.
 * This class provides static methods to retrieve various database-related configurations,
 * such as URI, database name, collection name, and collection ID.
 */

public class GetDB {

    /**
     * Retrieves the database URI from the specified file.
     *
     * @return The database URI as a string.
     */
    public static String getURI(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/DB_uri");
    }


    /**
     * Retrieves the database name from the specified file.
     *
     * @return The database name as a string.
     */
    public static String getDBName(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/DB_name");
    }


    /**
     * Retrieves the Events collection name from the specified file.
     *
     * @return The Events collection name as a string.
     */
    public static String getEventsCollection(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/EventsCollectionName");
    }

    /**
     * Retrieves the EventPosters collection name from the specified file.
     *
     * @return The EventsPosters collection name as a string.
     */
    public static String getEventPostersCollection(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/EventPostersCollectionName");
    }

    /**
     * Retrieves the Users collection name from the specified file.
     *
     * @return The Users collection name as a string.
     */
    public static String getUsersCollection(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/UsersCollectionName");
    }

    /**
     * Retrieves the Admins collection name from the specified file.
     *
     * @return The Admins collection name as a string.
     */
    public static String getAdminsCollection(){
        return readTokenFromFile("src/main/Java/data_access/readDB/DB_files/AdminsCollectionName");
    }

//    /**
//     * Retrieves the collection ID from the specified file.
//     *
//     * @return The collection ID as a string.
//     */
//    public static String getID(){
//        return readTokenFromFile("src/data_access/readDB/DB_files/CollectionID");
//    }


    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param fileName The path to the file to read.
     * @return The content of the file as a string.
     */
    private static String readTokenFromFile(String fileName) {
        StringBuilder token = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                token.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token.toString();
    }
}