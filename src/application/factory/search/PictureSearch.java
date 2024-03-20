package src.application.factory.search;

import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;

import java.util.List;

public class PictureSearch implements Search {
    
    private Filter filterPicture;
    private List<ISearchable> pictures;

    public PictureSearch(Filter filterPicture, List<ISearchable> pictures) {
        this.pictures = pictures;
        this.filterPicture = filterPicture;
        filter();
    }

    private void filter() {
        this.pictures = filterPicture.filter(pictures);
    }

    @Override  
    public  List<ISearchable> getISearchables() {
        return pictures;
    }

}
