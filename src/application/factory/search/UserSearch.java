package src.application.factory.search;

import java.util.List;

import src.domain.interfaces.ISearchable;
import src.domain.util.similarity.Filter;

public class UserSearch implements Search {

    private List<ISearchable> users;
    private final Filter filterUser;

    public UserSearch(Filter filterUser, List<ISearchable> users) {
        this.users = users;
        this.filterUser = filterUser;
        filter();

    }

    private void filter() {
        this.users = filterUser.filter(users);
    }

    @Override
    public List<ISearchable> getISearchables() {
        return users;
    }
    
}
