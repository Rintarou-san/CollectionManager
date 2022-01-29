package course.collectionmanager.service;

import course.collectionmanager.model.UserLike;

public interface LikeService {

    public void add(UserLike comment);

    public UserLike findItemLike(Long userId, Long itemId);

    public UserLike findCollectionLike(Long userId, Long collectionId);
}
