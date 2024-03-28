package src.domain.entities;

import src.domain.interfaces.ISearchable;

/**
 * Represents a hashtag
 */
public class HashTag implements ISearchable {

    /**
     * The name of the hashtag
     */
    private String name;
    /**
     * The picture the hashtag was used on
     */
    private Picture picture;

    /**
     * Create a new hashtag
     *
     * @param picture the picture the hashtag was used on
     * @param name the name of the hashtag
     */
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
