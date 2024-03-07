package src.domain.repositiories;

import src.domain.entities.Comment;

import java.util.List;
import java.util.UUID;

public interface ICommentRepository {
    List<Comment> findByPostId(UUID id);
    Comment create(Comment comment);
    Comment update(Comment comment);
    boolean delete(UUID id);
}
