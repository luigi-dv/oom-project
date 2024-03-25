package src.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import src.domain.entities.HashTag;
import src.infrastructure.utilities.file.reader.HashTagReader;
import src.infrastructure.utilities.file.writer.HashTagWriter;

public class HashTagRepository {

    public void save(HashTag hashTag) {
        HashTagWriter.writeLineToFile(hashTag);
    }

    public List<HashTag> getHashTagsByPictureId(UUID pictureId) {
        return HashTagReader.readHashTagsByPictureId(pictureId);
    }

    public List<HashTag> getAllHashTags() {
        return HashTagReader.readAllHashTags();
    }
}
