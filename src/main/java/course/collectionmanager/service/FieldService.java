package course.collectionmanager.service;

import course.collectionmanager.model.Field;
import java.util.List;

public interface FieldService {

    public List<Field> allFields();

    public Field findById(long id);
}
