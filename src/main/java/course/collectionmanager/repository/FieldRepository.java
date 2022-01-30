package course.collectionmanager.repository;

import course.collectionmanager.model.ItemField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<ItemField, Long> {

}
