package course.collectionmanager.service;

import course.collectionmanager.model.MyUser;
import course.collectionmanager.model.UserLike;
import course.collectionmanager.repository.LikeRepository;
import course.collectionmanager.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    
    @Autowired
    private LikeRepository repository;
    
    @Autowired
    private MyUserRepository userRepository;
    
    @Override
    public void add(UserLike like) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = userRepository.findByLogin(userInfo.getUsername());
        UserLike exLike = new UserLike();
        if (like.getItem() != null) {
            exLike = repository.findByUserIdAndItemId(user.getId(), like.getItem().getId());
        }
        if (like.getCollection() != null) {
            exLike = repository.findByUserIdAndCollectionId(user.getId(), like.getCollection().getId());
        }
        if (exLike == null) {
            like.setUser(user);
            repository.save(like);
        } else {
            repository.delete(exLike);
        }
    }
    
    @Override
    public UserLike findItemLike(Long userId, Long itemId) {
        return repository.findByUserIdAndItemId(userId, itemId);
    }
    
    @Override
    public UserLike findCollectionLike(Long userId, Long collectionId) {
        return repository.findByUserIdAndCollectionId(userId, collectionId);
    }
    
}
