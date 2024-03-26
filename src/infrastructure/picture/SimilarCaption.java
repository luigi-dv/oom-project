package src.infrastructure.picture;

import java.util.ArrayList;
import java.util.List;

import src.domain.entities.Picture;
import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;
import src.infrastructure.utilities.LevenshteinDistance;

public class SimilarCaption implements Filter {

    private final static double threshold = 0.6;
    private final String caption;

    public SimilarCaption(String caption) {
        this.caption = caption;
    }

    public List<ISearchable> filter(List<ISearchable> pictures) {
        List<ISearchable> similarCaption = new ArrayList<>();
        for (ISearchable picture : pictures) {
            Picture pic = (Picture) picture;
            int distance = LevenshteinDistance.calculate(caption, pic.getCaption());
            double similarity = 1 - ((double) distance / Math.max(caption.length(), pic.getCaption().length()));
            if (similarity >= threshold) {
                similarCaption.add(pic);
            }
        }
        return similarCaption;
    }
    
}
