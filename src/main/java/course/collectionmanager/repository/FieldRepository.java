package course.collectionmanager.repository;

import course.collectionmanager.model.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends CrudRepository<Field, Long>{

}
