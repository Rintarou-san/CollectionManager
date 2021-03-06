package course.collectionmanager.repository;

import course.collectionmanager.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<UserLike, Long> {

    public UserLike findByUserIdAndItemId(Long userId, Long itemId);

    public UserLike findByUserIdAndCollectionId(Long userId, Long collectionId);
}
