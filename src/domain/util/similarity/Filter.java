package src.domain.util.similarity;

import java.util.List;

import src.domain.interfaces.ISearchable;

public interface Filter {

    List<ISearchable> filter(List<ISearchable> pictures);
    
}
