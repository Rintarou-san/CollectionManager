package course.collectionmanager.service;

import course.collectionmanager.model.Collection;
import java.util.List;

public interface CollectionService {
    public List<Collection> allCollections();

    public void add(Collection collection);

    public void edit(Collection collection);

    public void delete(long id);

    public Collection getById(long id);
}
