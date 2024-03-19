package src.application.factory;

import java.util.List;

import src.application.factory.search.UserSearch;

import src.domain.interfaces.ISearchable;
import src.domain.util.similarity.Filter;

public class UserSearchFactory implements SearchFactory {

    private final Filter filterUser;
    private final List<ISearchable> users;

    public UserSearchFactory(Filter filterUser, List<ISearchable> users) {
        this.users = users;
        this.filterUser = filterUser;
    }

    public UserSearch createSearch() {
        return new UserSearch(filterUser, users);
    }
    
}
