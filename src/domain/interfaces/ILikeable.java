package src.domain.interfaces;
import java.util.UUID;

public interface ILikeable {

    /**
     * Get the unique identifier of the likeable entity
     * @return the unique identifier of the likeable entity
     */
    UUID getId();

    /**
     * Get the type of the likeable entity
     * @return the type of the likeable entity
     */
    String getType();
    
}
