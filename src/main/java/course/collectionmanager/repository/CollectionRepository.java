package course.collectionmanager.repository;

import course.collectionmanager.model.Collection;
import course.collectionmanager.model.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    public List<Collection> findByTags(Tag tag);

    @Query(value = "select *, count(i.id) as kol from collection c inner join item i on c.id=i.collection_id group by (c.id) order by kol desc limit 1", nativeQuery = true)
    public Collection largestCollection();
    
    @Query(value = "select *, count(l.id) as kol from collection c inner join user_like l on c.id=l.collection_id group by (c.id) order by kol desc limit 1", nativeQuery = true)
    public Collection likeCollection();
    
    @Query(value = "select *, count(com.id) as kol from collection c inner join comment com on c.id=com.collection_id group by (c.id) order by kol desc limit 1", nativeQuery = true)
    public Collection commentCollection();
}
