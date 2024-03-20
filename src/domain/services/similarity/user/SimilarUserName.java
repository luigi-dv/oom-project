package src.domain.services.similarity.user;

import java.util.ArrayList;
import java.util.List;

import src.domain.entities.User;
import src.domain.interfaces.ISearchable;
import src.domain.services.similarity.Filter;
import src.infrastructure.utilities.LevenshteinDistance;

public class SimilarUserName implements Filter {

    private final static double threshold = 0.6;
    private final String username;

    public SimilarUserName(String username) {
        this.username = username;
    }

    public List<ISearchable> filter(List<ISearchable> users) {
        List<ISearchable> similarUserNames = new ArrayList<>();
        for (ISearchable user : users) {
            User u = (User) user;
            int distance = LevenshteinDistance.calculate(username, u.getUsername());
            double similarity = 1 - ((double) distance / Math.max(username.length(), u.getUsername().length()));
            if (similarity >= threshold) {
                similarUserNames.add(u);
            }
        }
        return similarUserNames;
    }


    
}
