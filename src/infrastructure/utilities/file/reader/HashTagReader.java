package src.infrastructure.utilities.file.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import src.domain.entities.HashTag;
import src.domain.entities.Picture;
import src.infrastructure.utilities.file.IFile;

public class HashTagReader implements IFile {

    private static final String FILE_PATH = FILE_PATH_ROOT + "hashtag.txt";

    public static List<HashTag> readHashTagsByPictureId(UUID pictureId) {
        String stringPictureId = pictureId.toString();
        Picture picture = new Picture(pictureId);
        List<HashTag> hashTags = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringPictureId)) {
                    hashTags.add(parseHashTagFromLine(line, picture));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return hashTags;
    }

    private static HashTag parseHashTagFromLine(String line, Picture picture) {
        String[] parts = line.split(":");
        String hashTag = parts[1];
        return new HashTag(picture, hashTag);
    }

    public static List<HashTag> readAllHashTags() {
        List<HashTag> hashTags = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                hashTags.add(parseHashTagFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }
        return hashTags;
    }

    private static HashTag parseHashTagFromLine(String line) {
        String[] parts = line.split(":");
        UUID pictureId = UUID.fromString(parts[0]);
        String hashTag = parts[1];
        return new HashTag(new Picture(pictureId), hashTag);
    }
    
}
