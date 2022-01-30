package course.collectionmanager.repository;

import course.collectionmanager.model.Item;
import course.collectionmanager.model.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByTags(Tag tag);
}
