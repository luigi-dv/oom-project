package src.application.services;

import java.util.List;

import src.application.factory.PictureSearchFactory;
import src.application.factory.UserSearchFactory;
import src.application.factory.search.Search;
import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;
import src.domain.services.similarity.picture.SimilarCaption;
import src.domain.services.similarity.picture.SimiliarHashTags;
import src.domain.services.similarity.user.SimilarUserName;

public class SearchService {

    private final PictureService pictureService;
    private final UserService userService;

    public SearchService() {
        this.pictureService = new PictureService();
        this.userService = new UserService();
    }

    @SuppressWarnings("unchecked")
    public Search search(String searchQuery) {
        if (searchQuery.startsWith("@")) {
            Filter filterUsers = new SimilarUserName(searchQuery.substring(1));
            List<ISearchable> users = (List<ISearchable>) (List<?>) userService.getUsers();
            return new UserSearchFactory(filterUsers, users).createSearch();
        }

        Filter filterPicture;
        if (searchQuery.startsWith("#")) {
            filterPicture = new SimiliarHashTags(searchQuery.substring(1));
        } else {
            filterPicture = new SimilarCaption(searchQuery);
        }

        List<ISearchable> pictures = (List<ISearchable>) (List<?>) pictureService.getAllPictures();
        return new PictureSearchFactory(filterPicture, pictures).createSearch();

    }
    
}
