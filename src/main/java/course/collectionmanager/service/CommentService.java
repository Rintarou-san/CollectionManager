package course.collectionmanager.service;

import course.collectionmanager.model.Comment;
import java.util.List;

public interface CommentService {

    public List<Comment> allComments();

    public void add(Comment comment);
}
