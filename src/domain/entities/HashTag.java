package src.domain.entities;

import src.domain.interfaces.ISearchable;

public class HashTag implements ISearchable {

    private String name;
    private Picture picture;

    public HashTag(Picture picture, String name) {
        this.picture = picture;
        this.name = name;
    }

    public HashTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return picture.getId().toString() + ":" + name;
    }
    
}
