package src.domain.services.similarity.picture;

import java.util.List;
import java.util.ArrayList;

import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;

public class SimiliarHashTags implements Filter {

    private final static double threshold = 0.6;
    private final String hashTag;
    
    public SimiliarHashTags(String hashTag) {
        this.hashTag = hashTag;
    }

    // TODO: Implement filterSimiliarHashTags
    public List<ISearchable> filter(List<ISearchable> pictures) {
        return new ArrayList<>();
        // List<Picture> similiarHashTags = new ArrayList<>();
        // for (Picture picture : pictures) {
        //     int distance = LevenshteinDistance.calculate(hashTag, picture.getHashTag());
        //     double similarity = 1 - ((double) distance / Math.max(hashTag.length(), picture.getHashTag().length()));
        //     if (similarity >= threshold) {
        //         similiarHashTags.add(picture);
        //     }
        // }
        // return similiarHashTags;
    }
    
}
