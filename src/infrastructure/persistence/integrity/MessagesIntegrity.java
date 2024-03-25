package src.infrastructure.persistence.integrity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MessagesIntegrity {
    public boolean checkIntegrity(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Set<String> hashedPasswords = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length != 3) {
                    // Incorrect format, integrity check failed
                    return false;
                }
                String hashedPassword = parts[1];
                if (!hashedPasswords.add(hashedPassword)) {
                    // Duplicate hashed password found, integrity check failed
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Error occurred while reading the file, integrity check failed
            return false;
        }
        // Integrity check passed
        return true;
    }
}
