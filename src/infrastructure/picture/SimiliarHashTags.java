package src.infrastructure.picture;

import java.util.List;
import java.util.ArrayList;

import src.domain.entities.HashTag;
import src.domain.entities.Picture;
import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;
import src.infrastructure.utilities.LevenshteinDistance;

public class SimiliarHashTags implements Filter {

    private final static double threshold = 0.6;
    private final String hashTag;
    
    public SimiliarHashTags(String hashTag) {
        this.hashTag = hashTag;
    }

    public List<ISearchable> filter(List<ISearchable> hashTags) {
        List<ISearchable> similiarHashTags = new ArrayList<>();
        for (ISearchable iHashTag : hashTags) {
            Picture searchHashTag = (Picture) iHashTag;
            for (HashTag tag : searchHashTag.getHashTags()) {
                int distance = LevenshteinDistance.calculate(this.hashTag, tag.getName());
                double similarity = 1 - ((double) distance / Math.max(this.hashTag.length(), tag.getName().length()));
                if (similarity >= threshold) {
                    similiarHashTags.add(iHashTag);
                } 
            }
            
        }
        return similiarHashTags;
    }
    
}
