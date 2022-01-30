package course.collectionmanager.repository;

import course.collectionmanager.model.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {

}
