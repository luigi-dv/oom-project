package src.infrastructure.repositories;

import src.domain.repositiories.ICommentRepository;
import src.infrastructure.utilities.file.reader.CommentReader;
import src.infrastructure.utilities.file.writer.Comment;

import java.util.List;
import java.util.UUID;

public class CommentRepository implements ICommentRepository {

    /**
     * Find comments by the post id
     * @param id The id of the post
     * @return The list of comments
     */
    public List<src.domain.entities.Comment> findByPostId(UUID id){
        return CommentReader.getCommentsByPostId(id);
    }

    /**
     * Create a new comment
     * @param comment The comment to create
     * @return The created comment
     */
    public src.domain.entities.Comment create(src.domain.entities.Comment comment){
        return Comment.writeToFile(comment);
    }

    public src.domain.entities.Comment update(src.domain.entities.Comment comment){
        // TODO: Implement update
        return null;
    }
    public boolean delete(UUID id) {
        // TODO: Implement deletion
        return false;
    }
}
