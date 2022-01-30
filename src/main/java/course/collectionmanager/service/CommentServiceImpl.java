package course.collectionmanager.service;

import course.collectionmanager.model.Comment;
import course.collectionmanager.repository.CommentRepository;
import course.collectionmanager.repository.MyUserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private MyUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();
        repository.findAll().forEach(comments::add);
        return comments;
    }

    @Override
    @Transactional
    public void add(Comment comment) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(userRepository.findByLogin(userInfo.getUsername()));
        comment.setDate(new Date());
        repository.save(comment);
    }

}
