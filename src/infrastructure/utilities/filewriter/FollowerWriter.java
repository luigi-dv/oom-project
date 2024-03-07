package src.infrastructure.utilities.filewriter;


import java.io.FileWriter;
import java.io.BufferedWriter;
import src.domain.aggregate.Follow;


public class FollowerWriter {
    private static final String credentialsFilePath = "src/infrastructure/persistance/date/follow.txt";

    /**
     * Write a follow to the file.
     *
     * @param follow The follow to write to the file.
     * @return The follow that was written to the file.
     */
    public static Follow writeToFile(Follow follow) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFilePath, true))) {
            writer.write(follow.toString());
            writer.newLine();
            return follow;
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
            return null;
        }
    }


}
