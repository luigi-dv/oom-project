package src.infrastructure.utilities.file.writer;


import java.util.UUID;
import java.io.IOException;
import java.io.BufferedReader;

public interface IWriter {

    /**
     * Deletes the object from the file.
     *
     * @param objectId The object id to delete from the file.
     */
    static void deleteById(UUID objectId, BufferedReader reader) throws IOException {
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.startsWith(String.valueOf(objectId))) {
                out.append(line).append("\n");
            }
        }
    };
}
