package course.collectionmanager.repository;

import course.collectionmanager.model.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    
}
