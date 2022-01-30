package course.collectionmanager.service;

import course.collectionmanager.model.ItemField;
import java.util.List;

public interface FieldService {

    public List<ItemField> allFields();

    public ItemField findById(long id);
}
