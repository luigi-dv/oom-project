package src.application.factory;

import java.util.List;

import src.application.factory.search.PictureSearch;
import src.domain.interfaces.ISearchable;
import src.domain.util.similarity.Filter;

public class PictureSearchFactory implements SearchFactory {

    private final Filter filterPicture;
    private final List<ISearchable> pictures;

    
    public PictureSearchFactory(Filter filterPicture, List<ISearchable> pictures) {
        this.pictures = pictures;
        this.filterPicture = filterPicture;
    }
    
    public PictureSearch createSearch() {
        return new PictureSearch(filterPicture, pictures);
    }
    
}
