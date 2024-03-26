package src.application.services;

import java.util.List;
import java.util.UUID;

import src.domain.entities.HashTag;
import src.infrastructure.repositories.HashTagRepository;

public class HashTagService {

    private final HashTagRepository hashTagRepository;

    public HashTagService() {
        this.hashTagRepository = new HashTagRepository();
    }

    public void saveHashTags(List<HashTag> hashTags) {
        for (HashTag hashTag : hashTags) {
            hashTagRepository.save(hashTag);
        }
    }

    public List<HashTag> getHashTags() {
        return hashTagRepository.getAllHashTags();
    }

    public List<HashTag> getHashTagsByPictureId(UUID pictureId) {
        return hashTagRepository.getHashTagsByPictureId(pictureId);
    }
    
}
