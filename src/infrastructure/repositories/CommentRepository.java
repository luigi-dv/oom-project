package src.infrastructure.repositories;

import src.domain.entities.Comment;
import src.domain.repositiories.ICommentRepository;
import src.infrastructure.utilities.filereaders.CommentReader;
import src.infrastructure.utilities.filewriter.CommentWriter;

import java.util.List;
import java.util.UUID;

public class CommentRepository implements ICommentRepository {

    /**
     * Find comments by the post id
     * @param id The id of the post
     * @return The list of comments
     */
    public List<Comment> findByPostId(UUID id){
        return CommentReader.getCommentsByPostId(id);
    }

    /**
     * Create a new comment
     * @param comment The comment to create
     * @return The created comment
     */
    public Comment create(Comment comment){
        return CommentWriter.writeToFile(comment);
    }

    public Comment update(Comment comment){
        // TODO: Implement update
        return null;
    }
    public boolean delete(UUID id) {
        // TODO: Implement deletion
        return false;
    }
}
